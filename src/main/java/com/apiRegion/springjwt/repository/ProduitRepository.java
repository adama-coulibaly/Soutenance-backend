package com.apiRegion.springjwt.repository;

import com.apiRegion.springjwt.models.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long > {

    public Produit findByReference(String reference);
}
