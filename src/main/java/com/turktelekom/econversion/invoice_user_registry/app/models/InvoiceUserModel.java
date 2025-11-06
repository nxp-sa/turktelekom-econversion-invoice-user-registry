package com.turktelekom.econversion.invoice_user_registry.app.models;

import java.util.Date;

public record InvoiceUserModel(String identifier, String title, Date firstCreationTime, String aliasName,
                               Date aliasCreationTime) {
    public InvoiceUserModel changeAlias(String newAliasName, Date newAliasCreationTime) {
        return new InvoiceUserModel(identifier, title, firstCreationTime, newAliasName, newAliasCreationTime);
    }
}
