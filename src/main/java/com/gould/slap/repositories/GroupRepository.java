package com.gould.slap.repositories;

import com.gould.slap.domain.GroupEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends CrudRepository<GroupEntity, Long> {
}
