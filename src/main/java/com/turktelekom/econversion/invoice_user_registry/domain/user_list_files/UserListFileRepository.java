package com.turktelekom.econversion.invoice_user_registry.domain.user_list_files;

public interface UserListFileRepository {
    UserListFile getLatestUserListFile();
    Boolean checkToExists(String checksumId);
    void save(UserListFile userListFile);
}
