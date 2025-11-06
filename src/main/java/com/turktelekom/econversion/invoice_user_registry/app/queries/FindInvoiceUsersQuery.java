package com.turktelekom.econversion.invoice_user_registry.app.queries;

import com.turktelekom.econversion.invoice_user_registry.app.models.InvoiceUserModel;
import com.turktelekom.econversion.cqrs.core.Request;

import java.util.List;

public record FindInvoiceUsersQuery(String keyword, FindOptions options)
        implements Request<List<InvoiceUserModel>> {
    public enum FindOptions {
        IDENTIFIER,
        TITLE,
        ALIAS
    }
}
