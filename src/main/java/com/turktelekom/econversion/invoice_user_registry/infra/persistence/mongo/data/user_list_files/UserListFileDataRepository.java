package com.turktelekom.econversion.invoice_user_registry.infra.persistence.mongo.data.user_list_files;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserListFileDataRepository extends MongoRepository<UserListFileData, String>{
    UserListFileData findUserListFileByChecksumId(String checksumId);
    UserListFileData findTopByOrderByCreatedDateTimeDesc();
}
