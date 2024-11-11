package com.gould.slap.repositories;

import com.gould.slap.domain.PasswordResetRequestEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordResetRequestRepository extends CrudRepository<PasswordResetRequestEntity, Long> {
}
