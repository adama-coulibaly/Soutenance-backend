package com.apiRegion.springjwt.repository;

import com.apiRegion.springjwt.models.Formation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormationRepository extends JpaRepository<Formation,Long> {

    Formation findByTitreformation(String titreformation);
}
