package com.gould.slap.services;

import com.gould.slap.domain.GroupEntity;
import com.gould.slap.repositories.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class GroupService {

    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public GroupEntity createGroup(GroupEntity groupEntity) {
        return groupRepository.save(groupEntity);
    }

    public List<GroupEntity> findAll() {
        return StreamSupport.stream(groupRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Optional<GroupEntity> findById(Long id) {
        return groupRepository.findById(id);
    }

    public GroupEntity save(GroupEntity groupEntity) {
        return groupRepository.save(groupEntity);
    }

    public void delete(Long id) {
        groupRepository.deleteById(id);
    }
}
