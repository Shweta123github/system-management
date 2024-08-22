package com.eventregistration.system.controller;

import com.eventregistration.system.constants.AppUserErrorMessages;

import com.eventregistration.system.entity.AppUser;
import com.eventregistration.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ROLE_ADMIN') or #id == principal.id")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody AppUser userDetails, Principal principal) {
        userService.updateUser(id, userDetails);
        return ResponseEntity.status(AppUserErrorMessages.SUCCESSFULLY_UPDATED.getStatus()).body(AppUserErrorMessages.SUCCESSFULLY_UPDATED.getMessage());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.status(AppUserErrorMessages.SUCCESSFULLY_DELETED.getStatus()).body(AppUserErrorMessages.SUCCESSFULLY_DELETED.getMessage());
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<AppUser> getUserById(@PathVariable Long id) {
        Optional<AppUser> user = userService.findById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(AppUserErrorMessages.USER_NOT_FOUND.getStatus()).body(null));
    }
}
