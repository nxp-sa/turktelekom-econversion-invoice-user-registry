package com.turktelekom.econversion.invoice_user_registry.app.services;

import com.turktelekom.econversion.invoice_user_registry.domain.user_list_files.UserListFile;

import java.io.IOException;
import java.net.URISyntaxException;

public interface UserListFileDownloadService {
    UserListFile downloadUserListFile(String sourceUrlString, String tempDir)
            throws URISyntaxException, IOException;
}
