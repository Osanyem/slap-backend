package com.gould.slap.services;

import com.gould.slap.domain.PasswordResetRequestEntity;
import com.gould.slap.repositories.PasswordResetRequestRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PasswordResetRequestService {

    private final PasswordResetRequestRepository passwordResetRequestRepository;

    public PasswordResetRequestService(PasswordResetRequestRepository passwordResetRequestRepository) {
        this.passwordResetRequestRepository = passwordResetRequestRepository;
    }

    public PasswordResetRequestEntity createRequest(PasswordResetRequestEntity passwordResetRequest) {
        return passwordResetRequestRepository.save(passwordResetRequest);
    }


    public Optional<PasswordResetRequestEntity> findById(Long id) {
        return passwordResetRequestRepository.findById(id);
    }


}
