package com.turktelekom.econversion.invoice_user_registry.infra.web.controllers;

import com.turktelekom.econversion.invoice_user_registry.app.models.InvoiceUserModel;
import com.turktelekom.econversion.invoice_user_registry.app.models.LatestDownloadInfoModel;
import com.turktelekom.econversion.invoice_user_registry.app.queries.FindInvoiceUsersQuery;
import com.turktelekom.econversion.invoice_user_registry.app.queries.GetLatestDownloadInfoQuery;
import com.turktelekom.econversion.cqrs.core.Sender;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user-list")
@RequiredArgsConstructor
public class DefaultController {
    final Sender sender;

    @GetMapping("latest-download")
    public ResponseEntity<LatestDownloadInfoModel> getLatestDownloadInfo() {
        var model = sender.send(new GetLatestDownloadInfoQuery());
        return model != null ? ResponseEntity.ok(model)
                : ResponseEntity.badRequest().build();
    }

    @GetMapping("find-users")
    public ResponseEntity<List<InvoiceUserModel>> findUsers(
            @RequestBody FindInvoiceUsersQuery query) {
        var model = sender.send(query);
        return model != null ? ResponseEntity.ok(model)
                : ResponseEntity.badRequest().build();
    }
}
