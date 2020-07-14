package com.swm.sprint1.controller;


import com.swm.sprint1.domain.User;
import com.swm.sprint1.exception.ResourceNotFoundException;
import com.swm.sprint1.repository.UserRepository;
import com.swm.sprint1.security.CurrentUser;
import com.swm.sprint1.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }

    @GetMapping("api/v1/user")
    @PreAuthorize("hasRole('USER')")
    public List<User> getUserList(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findAllCustom();
    }


}
