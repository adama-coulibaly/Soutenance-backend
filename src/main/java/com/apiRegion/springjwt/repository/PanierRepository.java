package com.apiRegion.springjwt.repository;

import com.apiRegion.springjwt.models.Panier;
import com.apiRegion.springjwt.models.Produit;
import com.apiRegion.springjwt.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PanierRepository extends JpaRepository<Panier,Long> {

    Optional<Panier> findByUser(User user);
    Optional<Panier> findByProduits(Produit produit);
}
