package com.apiRegion.springjwt.repository;

import com.apiRegion.springjwt.models.Mortalite;
import com.apiRegion.springjwt.models.Production;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MortaliteRepository extends JpaRepository<Mortalite,Long> {
    public List<Mortalite> findByProduction(Production production);
}
