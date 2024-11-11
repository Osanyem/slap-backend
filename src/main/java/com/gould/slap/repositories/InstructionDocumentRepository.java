package com.gould.slap.repositories;

import com.gould.slap.domain.InstructionDocumentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructionDocumentRepository extends CrudRepository<InstructionDocumentEntity, Long> {
}
