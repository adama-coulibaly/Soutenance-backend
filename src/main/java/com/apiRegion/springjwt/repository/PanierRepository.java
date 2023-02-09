package com.apiRegion.springjwt.repository;

import com.apiRegion.springjwt.models.Panier;
import com.apiRegion.springjwt.models.Produit;
import com.apiRegion.springjwt.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PanierRepository extends JpaRepository<Panier,Long> {

    List<Panier> findByUser(User user);

    List<Panier> findByUserAndEtat(User user,boolean etat);
  //  Panier findByProduits(Produit produit);
    List<Panier> findByEtat(boolean etat);
    Panier findByProduitsAndUser(Produit produit, User user);

    Panier findByProduitsAndUserAndEtat(Produit produit, User user,boolean etat);


    //Panier findByProduitsAndUserAndEtat(Produit produit, User user,boolean etat);

    Boolean existsByProduitsAndUser(Produit produit, User user);

    // ICI ON RECUPER LE TOTAL DES PRODUITS AJOUTE AU PANIER D'UN SEUL UTILISATEUR
    @Query(value = "SELECT SUM(panier.quantite) AS TotalProduit, SUM(panier.totalproduit) AS PrixTotaux FROM panier,users WHERE panier.user_id = users.id AND users.id=:user_id",nativeQuery = true)
    public Object detail(Long user_id);

    Boolean existsByProduitsAndUserAndEtat(Produit produit, User user, boolean b);
}
