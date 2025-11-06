package com.turktelekom.econversion.invoice_user_registry.infra.persistence.mongo.data.user_list_files;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "user_list_files")
public record UserListFileData(
        @Indexed String checksumId,
        String filePath,
        LocalDateTime createdDateTime,
        long contentSize,
        int usersCount) {
}