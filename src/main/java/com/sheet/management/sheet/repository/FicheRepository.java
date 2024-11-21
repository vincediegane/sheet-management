package com.sheet.management.sheet.repository;

import com.sheet.management.sheet.model.Fiche;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface FicheRepository extends JpaRepository<Fiche, Long> {
  @Query("SELECT f FROM Fiche f WHERE f.profile.id = :profileId")
  List<Fiche> findProfileFiches(@Param("profileId") Long profileId);
}
