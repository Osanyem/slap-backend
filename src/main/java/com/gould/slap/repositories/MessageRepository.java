package com.gould.slap.repositories;

import com.gould.slap.domain.MessageEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CrudRepository<MessageEntity, Long> {
}
