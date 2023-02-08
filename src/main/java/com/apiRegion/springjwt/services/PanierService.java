package com.apiRegion.springjwt.services;

import com.apiRegion.springjwt.Message.ReponseMessage;
import com.apiRegion.springjwt.models.*;

public interface PanierService {

    ReponseMessage Ajouter(Panier panier, Produit produit, User user);

    ReponseMessage Modifier(Panier panier, Produit produit, User user);
    ReponseMessage Supprimer(Panier panier, Produit produit, Long user);
}
