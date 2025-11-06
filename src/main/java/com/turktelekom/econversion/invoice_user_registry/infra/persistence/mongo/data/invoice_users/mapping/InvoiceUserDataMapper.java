package com.turktelekom.econversion.invoice_user_registry.infra.persistence.mongo.data.invoice_users.mapping;

import com.turktelekom.econversion.invoice_user_registry.domain.invoice_users.InvoiceUser;
import com.turktelekom.econversion.invoice_user_registry.domain.invoice_users.UserAlias;
import com.turktelekom.econversion.invoice_user_registry.infra.persistence.mongo.data.invoice_users.InvoiceUserData;
import com.turktelekom.econversion.invoice_user_registry.infra.persistence.mongo.data.invoice_users.UserAliasData;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InvoiceUserDataMapper {
    List<InvoiceUser> toDomainInvoiceUsers(List<InvoiceUserData> dataInvoiceUsers);
    InvoiceUser toDomainInvoiceUser(InvoiceUserData dataInvoiceUser);
    List<UserAlias> toDomainUserAliases(List<UserAliasData> dataUserAliases);
    UserAlias toDomainUserAlias(UserAliasData dataUserAliase);

    List<InvoiceUserData> toDataInvoiceUsers(List<InvoiceUser> domainInvoiceUsers);
    InvoiceUserData toDataInvoiceUser(InvoiceUser domainInvoiceUser);
    List<UserAliasData> toDataUserAliases(List<UserAlias> domainUserAliases);
    UserAliasData toDataUserAlias(UserAlias domainUserAlias);
}
