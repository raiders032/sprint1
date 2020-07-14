package com.swm.sprint1.repository;

import com.swm.sprint1.domain.UserCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCategoryRepository extends JpaRepository<UserCategory,Long>, UserCategoryRepositoryCustom {
}
