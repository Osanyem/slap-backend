package com.gould.slap.controllers;

import com.gould.slap.domain.PasswordResetRequestEntity;
import com.gould.slap.services.PasswordResetRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/password-reset-requests")
public class PasswordResetRequestController {

    private final PasswordResetRequestService passwordResetRequestService;

    public PasswordResetRequestController(PasswordResetRequestService passwordResetRequestService) {
        this.passwordResetRequestService = passwordResetRequestService;
    }

    @PostMapping(path = "/password-reset-requests")
    public ResponseEntity<PasswordResetRequestEntity> createRequest(@RequestBody PasswordResetRequestEntity passwordResetRequest) {
        PasswordResetRequestEntity createdRequest = passwordResetRequestService.createRequest(passwordResetRequest);
        return new ResponseEntity<>(createdRequest, HttpStatus.CREATED);
    }

    @GetMapping("/password-reset-requests/{id}")
    public ResponseEntity<PasswordResetRequestEntity> getRequestById(@PathVariable Long id) {
        Optional<PasswordResetRequestEntity> request = passwordResetRequestService.findById(id);
        return request.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
