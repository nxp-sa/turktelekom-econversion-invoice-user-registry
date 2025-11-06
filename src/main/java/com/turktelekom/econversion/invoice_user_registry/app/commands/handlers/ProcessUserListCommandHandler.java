package com.turktelekom.econversion.invoice_user_registry.app.commands.handlers;

import com.turktelekom.econversion.invoice_user_registry.app.commands.ProcessUserListCommand;
import com.turktelekom.econversion.invoice_user_registry.app.services.UserListFileParseService;
import com.turktelekom.econversion.invoice_user_registry.domain.invoice_users.InvoiceUser;
import com.turktelekom.econversion.invoice_user_registry.domain.invoice_users.InvoiceUserRepository;
import com.turktelekom.econversion.invoice_user_registry.domain.user_list_files.UserListFile;
import com.turktelekom.econversion.invoice_user_registry.domain.user_list_files.UserListFileRepository;
import com.turktelekom.econversion.cqrs.core.RequestHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProcessUserListCommandHandler implements RequestHandler<ProcessUserListCommand, Boolean> {
    final UserListFileParseService userListFileParseService;
    final UserListFileRepository userListFileRepository;
    final InvoiceUserRepository invoiceUserRepository;

    @Transactional
    void saveChanges(UserListFile userListFile, List<InvoiceUser> invoiceUsers) {
        userListFileRepository.save(userListFile);
        invoiceUserRepository.deleteAll();

        int chunkSize = 5000;
        for (int i = 0; i < invoiceUsers.size(); i += chunkSize) {
            int end = Math.min(i + chunkSize, invoiceUsers.size());
            List<InvoiceUser> chunk = invoiceUsers.subList(i, end);

            if (!chunk.isEmpty())
                invoiceUserRepository.saveAll(chunk);
        }
    }

    @Override
    public Boolean handle(ProcessUserListCommand processUserListCommand) {
        try {
            var userListFile = processUserListCommand.userListFile();

            if (userListFileRepository.checkToExists(userListFile.getChecksumId())) {
                log.info("There are no changes in the User-List File -> {}",
                        String.format("%s (%,d bytes)", userListFile.getChecksumId(), userListFile.getContentSize()));
                return false;
            }

            var invoiceUsers = userListFileParseService.parseUserListFile(userListFile);

            userListFile.updateUsersCount(invoiceUsers.size());

            saveChanges(userListFile, invoiceUsers);

            return true;
        } catch (Exception exc) {
            log.error("An error occurred while parsing the User-List File", exc);

            return false;
        }
    }
}
