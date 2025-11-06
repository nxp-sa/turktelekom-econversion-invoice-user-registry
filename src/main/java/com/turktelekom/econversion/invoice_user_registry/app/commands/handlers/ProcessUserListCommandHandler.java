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

    static final int GROUP_SIZE = 64;

    Hashtable<Integer, List<InvoiceUser>> getGroupedInvoiceUsers(List<InvoiceUser> invoiceUsers) {
        Hashtable<Integer, List<InvoiceUser>> groupedInvoiceUsers = new Hashtable<>();

        List<InvoiceUser> buffer;
        for (int i = 0; i < invoiceUsers.size(); i++) {
            var key = i % GROUP_SIZE;

            buffer = groupedInvoiceUsers.get(key);

            if (buffer == null)
                buffer = new ArrayList<>();

            buffer.add(invoiceUsers.get(i));
            groupedInvoiceUsers.put(key, buffer);
        }

        return groupedInvoiceUsers;
    }

    @Transactional
    void saveChanges(UserListFile userListFile, Hashtable<Integer, List<InvoiceUser>> groupedInvoiceUsers) {
        userListFileRepository.save(userListFile);
        invoiceUserRepository.deleteAll();

        Set<Integer> keys = groupedInvoiceUsers.keySet();
        for (Integer key : keys) {
            List<InvoiceUser> invoiceUsers = groupedInvoiceUsers.get(key);
            if (invoiceUsers != null && !invoiceUsers.isEmpty())
                invoiceUserRepository.saveAll(invoiceUsers);
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

            saveChanges(userListFile,
                    getGroupedInvoiceUsers(invoiceUsers));

            return true;
        } catch (Exception exc) {
            log.error("An error occurred while parsing the User-List File", exc);

            return false;
        }
    }
}
