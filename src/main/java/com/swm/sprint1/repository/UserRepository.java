package com.swm.sprint1.repository;


import com.swm.sprint1.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> , UserRepositoryCustom{

    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);

}
