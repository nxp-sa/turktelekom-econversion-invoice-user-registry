package com.turktelekom.econversion.invoice_user_registry.infra.persistence.mongo.data.invoice_users;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "invoice_users")
public record InvoiceUserData(
        @Indexed String identifier,
        @Indexed String title,
        String type,
        Date firstCreationTime,
        String accountType,
        List<UserAliasData> aliases
) {
}
