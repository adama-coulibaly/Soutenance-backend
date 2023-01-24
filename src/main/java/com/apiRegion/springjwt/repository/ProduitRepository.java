package com.apiRegion.springjwt.repository;

import com.apiRegion.springjwt.models.CategorieProd;
import com.apiRegion.springjwt.models.Ferme;
import com.apiRegion.springjwt.models.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long > {

    public Produit findByReference(String reference);
    public List<Produit> findByFermes(Ferme ferme);
    List<Produit> findByCategorieProd(CategorieProd categorieProd);
  //  Produit findByProduit(Produit produit);
}
