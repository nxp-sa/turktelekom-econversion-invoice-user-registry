package com.turktelekom.econversion.invoice_user_registry.domain.invoice_users;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Getter
public class InvoiceUser {
    String identifier;
    String title;
    String type;
    Date firstCreationTime;
    String accountType;
    List<UserAlias> aliases;
}
