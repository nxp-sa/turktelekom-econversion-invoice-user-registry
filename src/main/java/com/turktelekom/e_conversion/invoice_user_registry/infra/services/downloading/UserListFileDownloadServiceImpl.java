package com.turktelekom.e_conversion.invoice_user_registry.infra.services.downloading;

import com.turktelekom.e_conversion.invoice_user_registry.app.helpers.FileHelper;
import com.turktelekom.e_conversion.invoice_user_registry.app.services.UserListFileDownloadService;
import com.turktelekom.e_conversion.invoice_user_registry.domain.user_list_files.UserListFile;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
@RequiredArgsConstructor
public class UserListFileDownloadServiceImpl implements UserListFileDownloadService {
    static final String TEMP_FILE_NAME = "UserPKList.zip";

    final RestTemplate restTemplate;

    @Override
    public UserListFile downloadUserListFile(String sourceUrlString, String tempDir)
            throws URISyntaxException, IOException {
        URI sourceUrl = URI.create(sourceUrlString);

        FileHelper.createDirectoryIfNotExists(tempDir);

        return restTemplate.execute(sourceUrl, HttpMethod.GET, null, response -> {
            Path tempFilePath = Paths.get(tempDir, TEMP_FILE_NAME);
            Files.copy(response.getBody(), tempFilePath, StandardCopyOption.REPLACE_EXISTING);

            try (ZipInputStream stream = new ZipInputStream(Files.newInputStream(tempFilePath))) {
                ZipEntry entry = stream.getNextEntry();

                if (entry != null) {
                    Path userListFilePath = Paths.get(tempDir, entry.getName());
                    Files.copy(stream, userListFilePath, StandardCopyOption.REPLACE_EXISTING);

                    return UserListFile.create(userListFilePath);
                }
            } finally {
                if (Files.exists(tempFilePath))
                    Files.delete(tempFilePath);
            }

            return null;
        });
    }
}
