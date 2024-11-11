package com.gould.slap.repositories;

import com.gould.slap.domain.ProjectEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<ProjectEntity, Long> {
}
