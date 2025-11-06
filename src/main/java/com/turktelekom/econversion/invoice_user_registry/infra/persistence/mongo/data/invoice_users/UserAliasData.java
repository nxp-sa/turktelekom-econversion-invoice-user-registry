package com.turktelekom.econversion.invoice_user_registry.infra.persistence.mongo.data.invoice_users;

import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Date;

public record UserAliasData(
        @Indexed String name,
        Date creationTime,
        Date deletionTime) {
}
