package com.sheet.management.sheet.repository;

import com.sheet.management.sheet.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
  Profile findByUserId(Long id);
}
