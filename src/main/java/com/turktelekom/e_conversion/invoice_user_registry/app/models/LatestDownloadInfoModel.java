package com.turktelekom.e_conversion.invoice_user_registry.app.models;

import java.time.LocalDateTime;

public record LatestDownloadInfoModel(String checksumId, LocalDateTime downloadDate, Long fileSize, Integer usersCount) {
}
