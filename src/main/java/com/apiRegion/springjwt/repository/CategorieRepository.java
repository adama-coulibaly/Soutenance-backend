package com.apiRegion.springjwt.repository;

import com.apiRegion.springjwt.models.CategorieProd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorieRepository extends JpaRepository<CategorieProd,Long> {
    CategorieProd findByNomcategories(String nomcategories);
}
