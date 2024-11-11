package com.gould.slap.repositories;

import com.gould.slap.domain.SlapEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlapRepository extends CrudRepository<SlapEntity, Long> {
}
