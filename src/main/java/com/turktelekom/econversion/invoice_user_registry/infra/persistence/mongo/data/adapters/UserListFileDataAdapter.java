package com.turktelekom.econversion.invoice_user_registry.infra.persistence.mongo.data.adapters;

import com.turktelekom.econversion.invoice_user_registry.domain.user_list_files.UserListFile;
import com.turktelekom.econversion.invoice_user_registry.domain.user_list_files.UserListFileRepository;
import com.turktelekom.econversion.invoice_user_registry.infra.persistence.mongo.data.user_list_files.UserListFileData;
import com.turktelekom.econversion.invoice_user_registry.infra.persistence.mongo.data.user_list_files.UserListFileDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class UserListFileDataAdapter implements UserListFileRepository {
    final UserListFileDataRepository userListFileDataRepository;

    @Override
    public UserListFile getLatestUserListFile() {
        var data = userListFileDataRepository.findTopByOrderByCreatedDateTimeDesc();
        return UserListFile.load(
                Paths.get(data.filePath()),
                data.checksumId(),
                data.createdDateTime(),
                data.contentSize(),
                data.usersCount());
    }

    @Override
    public Boolean checkToExists(String checksumId) {
        var data = userListFileDataRepository.findUserListFileByChecksumId(checksumId);
        return data != null;
    }

    @Override
    public void save(UserListFile userListFile) {
        var data = new UserListFileData(
                userListFile.getChecksumId(),
                userListFile.getFilePath().toString(),
                userListFile.getCreatedDateTime(),
                userListFile.getContentSize(),
                userListFile.getUsersCount());
        userListFileDataRepository.save(data);
    }
}
