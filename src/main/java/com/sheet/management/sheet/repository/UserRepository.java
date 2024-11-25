package com.sheet.management.sheet.repository;

import com.sheet.management.sheet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(@Param("email") String email);
    User findByProfileId(Long profileId);
}
