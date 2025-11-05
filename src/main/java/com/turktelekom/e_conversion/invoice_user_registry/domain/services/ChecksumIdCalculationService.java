package com.turktelekom.e_conversion.invoice_user_registry.domain.services;

import java.io.IOException;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;

public interface ChecksumIdCalculationService {
    String calculateChecksumId(Path filePath)
            throws IOException, NoSuchAlgorithmException;
}
