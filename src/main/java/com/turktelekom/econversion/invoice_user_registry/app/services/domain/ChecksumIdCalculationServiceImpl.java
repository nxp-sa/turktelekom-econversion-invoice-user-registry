package com.turktelekom.econversion.invoice_user_registry.app.services.domain;

import com.turktelekom.econversion.invoice_user_registry.app.helpers.FileHelper;
import com.turktelekom.econversion.invoice_user_registry.domain.services.ChecksumIdCalculationService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class ChecksumIdCalculationServiceImpl implements ChecksumIdCalculationService {
    @Override
    public String calculateChecksumId(Path filePath)
            throws IOException, NoSuchAlgorithmException {

        FileHelper.checkToFileForRead(filePath);

        MessageDigest md = MessageDigest.getInstance("SHA-256");

        try (InputStream is = Files.newInputStream(filePath)) {
            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = is.read(buffer)) != -1) {
                md.update(buffer, 0, bytesRead);
            }
        }

        byte[] digest = md.digest();
        StringBuilder hexString = new StringBuilder();

        for (byte b : digest)
            hexString.append(String.format("%02x", b));

        return hexString.toString();
    }
}
