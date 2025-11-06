package com.turktelekom.econversion.invoice_user_registry.domain.invoice_users;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class UserAlias {
    String name;
    Date creationTime;
    Date deletionTime;

    public Boolean isNotDeleted() {
        return deletionTime == null;
    }
}
