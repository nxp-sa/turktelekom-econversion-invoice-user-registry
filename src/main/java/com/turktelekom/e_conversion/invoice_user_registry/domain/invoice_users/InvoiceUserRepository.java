package com.turktelekom.e_conversion.invoice_user_registry.domain.invoice_users;

import java.util.List;

public interface InvoiceUserRepository {
    List<InvoiceUser> findByIdentifier(String identifier);
    List<InvoiceUser> findByTitle(String title);
    List<InvoiceUser> findByAlias(String alias);

    void deleteAll();
    void saveAll(List<InvoiceUser> invoiceUsers);
}
