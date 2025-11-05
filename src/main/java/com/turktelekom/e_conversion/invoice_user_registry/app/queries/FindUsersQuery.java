package com.turktelekom.e_conversion.invoice_user_registry.app.queries;

import com.turktelekom.e_conversion.invoice_user_registry.app.models.InvoiceUserModel;
import com.turktelekom.econversion.cqrs.core.Request;

import java.util.List;

public record FindUsersQuery(String keyword, FindOptions options)
        implements Request<List<InvoiceUserModel>> {
    public enum FindOptions {
        IDENTITY,
        TITLE,
        ALIAS
    }
}
