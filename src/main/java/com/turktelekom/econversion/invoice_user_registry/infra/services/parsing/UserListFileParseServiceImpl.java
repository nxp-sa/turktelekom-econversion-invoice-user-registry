package com.turktelekom.econversion.invoice_user_registry.infra.services.parsing;

import com.turktelekom.econversion.invoice_user_registry.app.services.UserListFileParseService;
import com.turktelekom.econversion.invoice_user_registry.domain.invoice_users.InvoiceUser;
import com.turktelekom.econversion.invoice_user_registry.domain.user_list_files.UserListFile;
import com.turktelekom.econversion.invoice_user_registry.infra.services.parsing.models.UserListRootElement;
import com.turktelekom.econversion.invoice_user_registry.infra.services.parsing.models.mapping.ParsingModelMapper;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserListFileParseServiceImpl implements UserListFileParseService {
    final ParsingModelMapper parsingModelMapper;

    UserListRootElement deserializeXmlFile(Path filePath)
            throws IOException, JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(UserListRootElement.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        try (Reader reader = Files.newBufferedReader(filePath)) {
            return (UserListRootElement) unmarshaller.unmarshal(reader);
        }
    }

    @Override
    public List<InvoiceUser> parseUserListFile(UserListFile userListFile)
            throws IOException {
        try {
            UserListRootElement root = deserializeXmlFile(userListFile.getFilePath());
            return parsingModelMapper.toDomainInvoiceUsers(root.getUsers());
        } catch (JAXBException exc) {
            throw new IOException("An error occurred during deserialization of the User-List File", exc);
        }
    }
}
