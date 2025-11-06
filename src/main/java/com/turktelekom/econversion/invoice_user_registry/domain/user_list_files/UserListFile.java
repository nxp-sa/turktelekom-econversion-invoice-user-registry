package com.turktelekom.econversion.invoice_user_registry.domain.user_list_files;

import com.turktelekom.econversion.invoice_user_registry.app.helpers.FileHelper;
import com.turktelekom.econversion.invoice_user_registry.domain.services.ChecksumIdCalculationService;
import lombok.Getter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;

@Getter
public class UserListFile {
    Path filePath;
    String checksumId;
    LocalDateTime createdDateTime;
    Long contentSize;
    Integer usersCount;

    UserListFile(Path filePath, Long contentSize) {
        this.filePath = filePath;
        this.contentSize = contentSize;
    }

    public void calculateChecksumId(ChecksumIdCalculationService checksumIdCalculator)
            throws Exception {
        checksumId = checksumIdCalculator.calculateChecksumId(filePath);
    }

    public void updateUsersCount(Integer usersCount) {
        this.usersCount = usersCount;
    }

    public static UserListFile create(Path filePath) throws IOException {
        File file = filePath.toFile();
        FileHelper.checkToFileForRead(file);

        var retval = new UserListFile(file.toPath(), file.length());
        retval.createdDateTime = LocalDateTime.now();

        return retval;
    }

    public static UserListFile load(Path filePath, String checksumId, LocalDateTime createdDateTime, Long contentSize, Integer usersCount){
        var userListFile = new UserListFile(filePath, contentSize);
        userListFile.checksumId = checksumId;
        userListFile.createdDateTime = createdDateTime;
        userListFile.usersCount = usersCount;
        return userListFile;
    }
}
