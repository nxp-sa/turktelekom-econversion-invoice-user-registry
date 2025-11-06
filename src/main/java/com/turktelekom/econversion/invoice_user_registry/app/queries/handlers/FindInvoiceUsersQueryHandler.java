package com.turktelekom.econversion.invoice_user_registry.app.queries.handlers;

import com.turktelekom.econversion.invoice_user_registry.app.models.InvoiceUserModel;
import com.turktelekom.econversion.invoice_user_registry.app.queries.FindInvoiceUsersQuery;
import com.turktelekom.econversion.invoice_user_registry.domain.invoice_users.InvoiceUser;
import com.turktelekom.econversion.invoice_user_registry.domain.invoice_users.InvoiceUserRepository;
import com.turktelekom.econversion.invoice_user_registry.domain.invoice_users.UserAlias;
import com.turktelekom.econversion.cqrs.core.RequestHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class FindInvoiceUsersQueryHandler implements RequestHandler<FindInvoiceUsersQuery, List<InvoiceUserModel>> {
    final InvoiceUserRepository invoiceUserRepository;

    InvoiceUserModel mapToModelInvoiceUser(InvoiceUser invoiceUser) {
        var optUserAlias = invoiceUser.getAliases()
                .stream()
                .filter(UserAlias::isNotDeleted)
                .findFirst();

        InvoiceUserModel model = new InvoiceUserModel(
                invoiceUser.getIdentifier(),
                invoiceUser.getTitle(),
                invoiceUser.getFirstCreationTime(),
                null, null);

        if (optUserAlias.isEmpty())
            return model;

        var userAlias = optUserAlias.get();
        return model.changeAlias(userAlias.getName(), userAlias.getCreationTime());
    }

    @Override
    public List<InvoiceUserModel> handle(FindInvoiceUsersQuery findInvoiceUsersQuery) {
        try {
            List<InvoiceUser> invoiceUsers = switch (findInvoiceUsersQuery.options()) {
                case IDENTIFIER -> invoiceUserRepository.findByIdentifier(findInvoiceUsersQuery.keyword());
                case TITLE -> invoiceUserRepository.findByTitle(findInvoiceUsersQuery.keyword());
                case ALIAS -> invoiceUserRepository.findByAlias(findInvoiceUsersQuery.keyword());
            };

            return invoiceUsers.stream()
                    .map(this::mapToModelInvoiceUser)
                    .collect(Collectors.toList());
        } catch (Exception exc) {
            log.error("An error occurred on Find Users Query handling -> {}",
                    exc.getMessage());
            return List.of();
        }
    }
}
