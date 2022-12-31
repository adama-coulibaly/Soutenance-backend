package com.apiRegion.springjwt.repository;

import com.apiRegion.springjwt.models.Mortalite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MortaliteRepository extends JpaRepository<Mortalite,Long> {
}
