package com.gould.slap.repositories;

import com.gould.slap.domain.CourseEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CrudRepository<CourseEntity, Long> {
}
