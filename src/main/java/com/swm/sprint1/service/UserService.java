package com.swm.sprint1.service;

import com.swm.sprint1.domain.AuthProvider;
import com.swm.sprint1.domain.User;
import com.swm.sprint1.domain.UserCategory;
import com.swm.sprint1.exception.ResourceNotFoundException;
import com.swm.sprint1.payload.request.UpdateUserCategoryRequest;
import com.swm.sprint1.payload.request.SignUpRequest;
import com.swm.sprint1.repository.CategoryRepository;
import com.swm.sprint1.repository.UserCategoryRepository;
import com.swm.sprint1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final UserCategoryRepository userCategoryRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User createUser(SignUpRequest signUpRequest) {
        User user = new User(signUpRequest.getName(),
                signUpRequest.getEmail(),
                passwordEncoder.encode(signUpRequest.getPassword()),
                AuthProvider.local);
        return userRepository.save(user);
    }

    @Transactional
    public void updateUser(Long id, String name) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", id));
        user.changeName(name);
    }

    @Transactional
    public void updateUserCategories(Long id, UpdateUserCategoryRequest updateUserCategoryRequest) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", id));

        userCategoryRepository.deleteUserCategory(user.getId());
        Set<UserCategory> userCategories = updateUserCategoryRequest.getCategories().stream()
                .map(name -> new UserCategory(categoryRepository.findByName(name))).collect(Collectors.toSet());
        user.changeUserCategory(userCategories);
        userCategories.forEach(userCategory -> userCategoryRepository.save(userCategory));
    }

    public List<String> getUserCategoryName(Long id) {
        return userCategoryRepository.findCategoryNameByUserId(id);
    }
}
