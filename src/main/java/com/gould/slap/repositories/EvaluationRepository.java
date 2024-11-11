package com.gould.slap.repositories;

import com.gould.slap.domain.EvaluationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluationRepository extends CrudRepository<EvaluationEntity, Long> {
}
