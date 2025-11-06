package com.turktelekom.econversion.invoice_user_registry.app.queries.handlers;

import com.turktelekom.econversion.invoice_user_registry.app.models.LatestDownloadInfoModel;
import com.turktelekom.econversion.invoice_user_registry.app.queries.GetLatestDownloadInfoQuery;
import com.turktelekom.econversion.invoice_user_registry.domain.user_list_files.UserListFileRepository;
import com.turktelekom.econversion.cqrs.core.RequestHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetLatestDownloadInfoQueryHandler implements RequestHandler<GetLatestDownloadInfoQuery, LatestDownloadInfoModel> {
    final UserListFileRepository userListFileRepository;

    @Override
    public LatestDownloadInfoModel handle(GetLatestDownloadInfoQuery getLatestDownloadInfoQuery) {
        try {
            var latestUserListFile = userListFileRepository.getLatestUserListFile();
            return latestUserListFile == null ? null : new LatestDownloadInfoModel(
                    latestUserListFile.getChecksumId(),
                    latestUserListFile.getCreatedDateTime(),
                    latestUserListFile.getContentSize(),
                    latestUserListFile.getUsersCount());
        } catch (Exception exc) {
            log.error("An error occurred on Get Latest Download Info Query handling", exc);
            return null;
        }
    }
}
