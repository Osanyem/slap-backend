package com.gould.slap.controllers;

import com.gould.slap.domain.GroupEntity;
import com.gould.slap.services.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping(path = "/groups")
    public ResponseEntity<GroupEntity> createGroup(@RequestBody GroupEntity groupEntity) {
        GroupEntity createdGroup = groupService.createGroup(groupEntity);
        return new ResponseEntity<>(createdGroup, HttpStatus.CREATED);
    }

    @GetMapping(path = "/groups")
    public List<GroupEntity> listGroups() {
        return groupService.findAll();
    }

    @GetMapping("/groups/{id}")
    public ResponseEntity<GroupEntity> getGroup(@PathVariable Long id) {
        Optional<GroupEntity> group = groupService.findById(id);
        return group.map(g -> new ResponseEntity<>(g, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/groups/{id}")
    public ResponseEntity<GroupEntity> updateGroup(@PathVariable Long id, @RequestBody GroupEntity groupEntity) {
        Optional<GroupEntity> existingGroup = groupService.findById(id);
        if (existingGroup.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        groupEntity.setGroupId(id);
        GroupEntity updatedGroup = groupService.save(groupEntity);
        return new ResponseEntity<>(updatedGroup, HttpStatus.OK);
    }

    @DeleteMapping("/groups/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long id) {
        groupService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
