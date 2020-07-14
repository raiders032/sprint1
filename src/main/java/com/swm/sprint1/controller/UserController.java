package com.swm.sprint1.controller;


import com.swm.sprint1.domain.User;
import com.swm.sprint1.exception.ResourceNotFoundException;
import com.swm.sprint1.payload.ApiResponse;
import com.swm.sprint1.payload.UpdateUserCategoryRequest;
import com.swm.sprint1.payload.UserUpdateRequest;
import com.swm.sprint1.repository.UserRepository;
import com.swm.sprint1.security.CurrentUser;
import com.swm.sprint1.security.UserPrincipal;
import com.swm.sprint1.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }

    @GetMapping("/api/v1/user")
    @PreAuthorize("hasRole('USER')")
    public List<User> getUserList(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findAllCustom();
    }

    @PutMapping("/api/v1/user")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> updateUser(@CurrentUser UserPrincipal userPrincipal
            , @Valid @RequestBody UserUpdateRequest request){
        userService.updateUser(userPrincipal.getId(),request.getName());
        return ResponseEntity.ok(new ApiResponse(true,"nickname 수정 완료"));
    }

    @PostMapping("/api/v1/user/category")
    public ResponseEntity<?> updateUserCategories(@CurrentUser UserPrincipal userPrincipal
            , @Valid @RequestBody UpdateUserCategoryRequest updateUserCategoryRequest){
        userService.updateUserCategories(userPrincipal.getId(), updateUserCategoryRequest);
        return ResponseEntity.ok(new ApiResponse(true,"nickname 수정 완료"));
    }


}
