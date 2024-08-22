package com.eventregistration.system.service;

import com.eventregistration.system.constants.AppUserErrorMessages;
import com.eventregistration.system.entity.AppUser;
import com.eventregistration.system.exception.ResourceNotFoundException;
import com.eventregistration.system.exception.UserAlreadyExistsException;
import com.eventregistration.system.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private AppUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AppUser registerUser(AppUser user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException(AppUserErrorMessages.USER_ALREADY_EXISTS);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Optional<AppUser> findById(Long id) {
        return Optional.ofNullable(userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(AppUserErrorMessages.USER_NOT_FOUND)));
    }

    public AppUser updateUser(Long id, AppUser userDetails) {
        AppUser user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(AppUserErrorMessages.USER_NOT_FOUND));
        user.setUsername(userDetails.getUsername());
        user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        AppUser user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(AppUserErrorMessages.USER_NOT_FOUND));
        userRepository.delete(user);
    }
}
