package com.turktelekom.e_conversion.invoice_user_registry.app.commands;

import com.turktelekom.e_conversion.invoice_user_registry.domain.user_list_files.UserListFile;
import com.turktelekom.econversion.cqrs.core.Request;

import java.util.Optional;

public record DownloadUserListFileCommand(String sourceUrlString, String tempDirectory)
        implements Request<Optional<UserListFile>> {
}
