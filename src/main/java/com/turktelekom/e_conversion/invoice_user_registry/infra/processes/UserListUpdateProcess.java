package com.turktelekom.e_conversion.invoice_user_registry.infra.processes;

import com.turktelekom.e_conversion.invoice_user_registry.app.commands.DownloadUserListFileCommand;
import com.turktelekom.e_conversion.invoice_user_registry.app.commands.ProcessUserListCommand;
import com.turktelekom.econversion.cqrs.core.Sender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserListUpdateProcess {
    final Sender sender;

    @Value("${user-list.download.sourceUrl}")
    String sourceUrlString;

    @Value("${user-list.download.tempDir}")
    String tempDirectory;

    static final int FIXED_DELAY_VALUE = 2 * (60 * (60 * 1000));

    @Scheduled(fixedDelay = FIXED_DELAY_VALUE)
    public void run(){
        log.info("User-List Update Job is running ..");

        var downloadUserListFileCommand = new DownloadUserListFileCommand(sourceUrlString, tempDirectory);
        var optUserListFile = sender.send(downloadUserListFileCommand);

        if(optUserListFile.isEmpty())
            return;

        var processUserListCommand = new ProcessUserListCommand(optUserListFile.get());
        var affected = sender.send(processUserListCommand);

        log.info("User-List Update process completed {} changes.",
                affected ? "with" : "without");
    }
}
