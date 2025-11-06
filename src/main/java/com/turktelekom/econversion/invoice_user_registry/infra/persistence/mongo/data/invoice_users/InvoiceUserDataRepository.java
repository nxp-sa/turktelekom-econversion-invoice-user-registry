package com.turktelekom.econversion.invoice_user_registry.infra.persistence.mongo.data.invoice_users;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceUserDataRepository extends MongoRepository<InvoiceUserData, String> {
    List<InvoiceUserData> findUsersByIdentifier(String identifier);
    List<InvoiceUserData> findUsersByTitleContainingIgnoreCase(String title);
    List<InvoiceUserData> findUsersByAliases_Name(String aliasName);
}
