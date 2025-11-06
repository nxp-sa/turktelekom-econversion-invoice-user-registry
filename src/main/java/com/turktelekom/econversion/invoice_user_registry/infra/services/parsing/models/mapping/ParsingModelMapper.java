package com.turktelekom.econversion.invoice_user_registry.infra.services.parsing.models.mapping;

import com.turktelekom.econversion.invoice_user_registry.domain.invoice_users.InvoiceUser;
import com.turktelekom.econversion.invoice_user_registry.domain.invoice_users.UserAlias;
import com.turktelekom.econversion.invoice_user_registry.infra.services.parsing.models.AliasElement;
import com.turktelekom.econversion.invoice_user_registry.infra.services.parsing.models.DocumentElement;
import com.turktelekom.econversion.invoice_user_registry.infra.services.parsing.models.DocumentsListElement;
import com.turktelekom.econversion.invoice_user_registry.infra.services.parsing.models.UserElement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ParsingModelMapper {
    List<InvoiceUser> toDomainInvoiceUsers(List<UserElement> userElements);

    @Mapping(target = "aliases", source = "documents", qualifiedByName = "aliasesMapping")
    InvoiceUser toDomainInvoiceUser(UserElement userElement);

    UserAlias toDomainUserAlias(AliasElement aliasElement);

    @Named("aliasesMapping")
    default List<UserAlias> toDomainUserAliasesFromDocumentsAndsOnlyInvoiceType(DocumentsListElement documentsList) {
        var documents = documentsList.getDocuments();
        return documents.stream()
                .filter(DocumentElement::isInvoiceAndValid)
                .flatMap(document -> document.getAliases().stream())
                .map(this::toDomainUserAlias)
                .collect(Collectors.toList());
    }
}
