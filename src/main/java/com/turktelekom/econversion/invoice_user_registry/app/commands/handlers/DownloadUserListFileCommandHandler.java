package com.turktelekom.econversion.invoice_user_registry.app.commands.handlers;

import com.turktelekom.econversion.invoice_user_registry.app.commands.DownloadUserListFileCommand;
import com.turktelekom.econversion.invoice_user_registry.app.services.UserListFileDownloadService;
import com.turktelekom.econversion.invoice_user_registry.domain.services.ChecksumIdCalculationService;
import com.turktelekom.econversion.invoice_user_registry.domain.user_list_files.UserListFile;
import com.turktelekom.econversion.cqrs.core.RequestHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class DownloadUserListFileCommandHandler implements RequestHandler<DownloadUserListFileCommand, Optional<UserListFile>> {
    final UserListFileDownloadService userListFileDownloadService;
    final ChecksumIdCalculationService checksumIdCalculator;

    @Override
    public Optional<UserListFile> handle(DownloadUserListFileCommand downloadUserListFileCommand) {
        try {
            var userListFile = userListFileDownloadService.downloadUserListFile(
                    downloadUserListFileCommand.sourceUrlString(),
                    downloadUserListFileCommand.tempDirectory());

            userListFile.calculateChecksumId(checksumIdCalculator);

            return Optional.of(userListFile);
        } catch (Exception exc) {
            log.error("An error occurred while downloading the User-List File", exc);
            return Optional.empty();
        }
    }
}
