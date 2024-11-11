package com.gould.slap.repositories;

import com.gould.slap.domain.SubmissionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmissionRepository extends CrudRepository<SubmissionEntity, Long> {
}
