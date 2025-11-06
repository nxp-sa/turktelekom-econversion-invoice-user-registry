package com.turktelekom.econversion.invoice_user_registry.app.commands;

import com.turktelekom.econversion.invoice_user_registry.domain.user_list_files.UserListFile;
import com.turktelekom.econversion.cqrs.core.Request;

public record ProcessUserListCommand(UserListFile userListFile) implements Request<Boolean> {
}
