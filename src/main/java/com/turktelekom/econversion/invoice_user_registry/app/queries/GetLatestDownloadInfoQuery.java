package com.turktelekom.econversion.invoice_user_registry.app.queries;

import com.turktelekom.econversion.invoice_user_registry.app.models.LatestDownloadInfoModel;
import com.turktelekom.econversion.cqrs.core.Request;

public record GetLatestDownloadInfoQuery() implements Request<LatestDownloadInfoModel> {
}
