package com.turktelekom.econversion.invoice_user_registry.app.models;

import java.time.LocalDateTime;

public record LatestDownloadInfoModel(String checksumId, LocalDateTime downloadDate, Long fileSize, Integer usersCount) {
}
