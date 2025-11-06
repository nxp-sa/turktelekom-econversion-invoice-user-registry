package com.turktelekom.econversion.invoice_user_registry.app.services;

import com.turktelekom.econversion.invoice_user_registry.domain.invoice_users.InvoiceUser;
import com.turktelekom.econversion.invoice_user_registry.domain.user_list_files.UserListFile;

import java.io.IOException;
import java.util.List;

public interface UserListFileParseService {
    List<InvoiceUser> parseUserListFile(UserListFile userListFile)
            throws IOException;
}
