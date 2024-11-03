package com.gould.slap.controllers;

import com.gould.slap.domain.UserEntity;
import com.gould.slap.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/users")
    public UserEntity createUser(@RequestBody UserEntity user) {
        return userService.createUser(user);
    }

    @GetMapping(path = "/users")
    public List<UserEntity> listUsers() {
        List<UserEntity> users = userService.findAll();
        return users.stream()
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/users/{id}")
    public ResponseEntity<UserEntity> getUser(@PathVariable("id") Long id) {
        Optional<UserEntity> foundUser = userService.findOne(id);
        return foundUser.map(userEntity -> {
            return new ResponseEntity<>(userEntity, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/users/{id}")
    public ResponseEntity<UserEntity> fullUpdateUser(
            @PathVariable("id") Long id,
            @RequestBody UserEntity userEntity) {

        if(!userService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        userEntity.setUser_id(id);
        UserEntity savedUserEntity = userService.save(userEntity);
        return new ResponseEntity<>(
                savedUserEntity,
                HttpStatus.OK);
    }

    @PatchMapping(path = "/users/{id}")
    public ResponseEntity<UserEntity> partialUpdate(
            @PathVariable("id") Long id,
            @RequestBody UserEntity userEntity
    ) {
        if(!userService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        UserEntity updatedUser = userService.partialUpdate(id, userEntity);
        return new ResponseEntity<>(
                updatedUser,
                HttpStatus.OK);
    }

    @DeleteMapping(path = "/users/{id}")
    public ResponseEntity deleteAuthor(@PathVariable("id") Long id) {
        userService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
