package com.turktelekom.econversion.invoice_user_registry.domain.invoice_users;

import java.util.List;

public interface InvoiceUserRepository {
    List<InvoiceUser> findByIdentifier(String identifier);
    List<InvoiceUser> findByTitle(String title);
    List<InvoiceUser> findByAlias(String alias);

    void deleteAll();
    void saveAll(List<InvoiceUser> invoiceUsers);
}
