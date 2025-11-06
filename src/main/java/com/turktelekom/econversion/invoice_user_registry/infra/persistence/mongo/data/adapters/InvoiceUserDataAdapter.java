package com.turktelekom.econversion.invoice_user_registry.infra.persistence.mongo.data.adapters;

import com.turktelekom.econversion.invoice_user_registry.domain.invoice_users.InvoiceUser;
import com.turktelekom.econversion.invoice_user_registry.domain.invoice_users.InvoiceUserRepository;
import com.turktelekom.econversion.invoice_user_registry.infra.persistence.mongo.data.invoice_users.InvoiceUserDataRepository;
import com.turktelekom.econversion.invoice_user_registry.infra.persistence.mongo.data.invoice_users.mapping.InvoiceUserDataMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceUserDataAdapter implements InvoiceUserRepository {
    final InvoiceUserDataRepository invoiceUserDataRepository;
    final InvoiceUserDataMapper invoiceUserDataMapper;

    @Override
    public List<InvoiceUser> findByIdentifier(String identifier) {
        var data = invoiceUserDataRepository.findUsersByIdentifier(identifier);
        return invoiceUserDataMapper.toDomainInvoiceUsers(data);
    }

    @Override
    public List<InvoiceUser> findByTitle(String title) {
        var data = invoiceUserDataRepository.findUsersByTitleContainingIgnoreCase(title);
        return invoiceUserDataMapper.toDomainInvoiceUsers(data);
    }

    @Override
    public List<InvoiceUser> findByAlias(String alias) {
        var data = invoiceUserDataRepository.findUsersByAliases_Name(alias);
        return invoiceUserDataMapper.toDomainInvoiceUsers(data);
    }

    @Override
    public void deleteAll() {
        invoiceUserDataRepository.deleteAll();
    }

    @Override
    public void saveAll(List<InvoiceUser> invoiceUsers) {
        var data = invoiceUserDataMapper.toDataInvoiceUsers(invoiceUsers);
        invoiceUserDataRepository.saveAll(data);
    }
}
