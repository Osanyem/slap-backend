package com.gould.slap.services;

import com.gould.slap.domain.UserEntity;
import com.gould.slap.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity createUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }
    public UserEntity save(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    public List<UserEntity> findAll() {
        return StreamSupport
                .stream(
                        userRepository.findAll().spliterator(),
                        false)
                .collect(Collectors.toList());
    }

    public Optional<UserEntity> findOne(Long id) {
        return userRepository.findById(id);
    }

    public boolean isExists(Long id) {
        return userRepository.existsById(id);
    }

    public UserEntity partialUpdate(Long id, UserEntity userEntity) {
        userEntity.setUserId(id);

        return userRepository.findById(id).map(existingUser -> {
            Optional.ofNullable(userEntity.getFirstName()).ifPresent(existingUser::setFirstName);
            Optional.ofNullable(userEntity.getMiddleName()).ifPresent(existingUser::setMiddleName);
            Optional.ofNullable(userEntity.getLastName()).ifPresent(existingUser::setLastName);
            Optional.ofNullable(userEntity.getDateOfBirth()).ifPresent(existingUser::setDateOfBirth);
            Optional.ofNullable(userEntity.getUsername()).ifPresent(existingUser::setUsername);
            Optional.ofNullable(userEntity.getPassword()).ifPresent(existingUser::setPassword);
            Optional.ofNullable(userEntity.getRole()).ifPresent(existingUser::setRole);
            Optional.ofNullable(userEntity.getEmail()).ifPresent(existingUser::setEmail);

            // Save the updated user to the database
            return userRepository.save(existingUser);
        }).orElseThrow(() -> new RuntimeException("User does not exist"));
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

}
