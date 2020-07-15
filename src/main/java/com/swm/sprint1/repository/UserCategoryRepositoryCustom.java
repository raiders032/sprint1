package com.swm.sprint1.repository;

import com.swm.sprint1.domain.Category;
import com.swm.sprint1.domain.User;

import java.util.List;

public interface UserCategoryRepositoryCustom {
    List<Category> findCategoryByUserId(Long userId);
    List<String> findCategoryNameByUserId(Long userId);

    long deleteUserCategory(Long userId);
}
