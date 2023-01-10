package com.apiRegion.springjwt.services;

import com.apiRegion.springjwt.Message.ReponseMessage;
import com.apiRegion.springjwt.models.Ferme;
import com.apiRegion.springjwt.models.Produit;
import com.apiRegion.springjwt.models.User;

import java.util.List;

public interface ProduitService {


    ReponseMessage Ajouter(Produit produit, Ferme ferme
    );

    ReponseMessage Modifier(Produit produit, Long idferme);

    ReponseMessage Supprimer(Long idproduit);

    ReponseMessage SetEtat(Produit produit, Long idferme);

    List<Produit> Lister();
}
