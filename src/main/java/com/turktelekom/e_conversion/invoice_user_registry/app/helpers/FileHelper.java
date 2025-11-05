package com.turktelekom.e_conversion.invoice_user_registry.app.helpers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class FileHelper {
    public static void checkToFileForRead(Path filePath)
            throws IOException {
        checkToFileForRead(filePath.toFile());
    }

    public static void checkToFileForRead(File file)
            throws IOException {

        boolean flag = true;
        flag &= file.isFile();
        flag &= file.exists();
        flag &= file.canRead();

        if (!flag) {
            var error = String.format("This file does not exist or is not readable. ('%s')", file.getPath());
            throw new IOException(error);
        }
    }

    public static void createDirectoryIfNotExists(String directory)
            throws IOException {
        Path directoryPath = Paths.get(directory)
                .toAbsolutePath();

        if (!Files.exists(directoryPath))
            Files.createDirectory(directoryPath);
    }
}
