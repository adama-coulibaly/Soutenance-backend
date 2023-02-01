package com.apiRegion.springjwt.services;

import com.apiRegion.springjwt.Message.ReponseMessage;
import com.apiRegion.springjwt.models.Panier;
import com.apiRegion.springjwt.models.Produit;
import com.apiRegion.springjwt.models.User;
import com.apiRegion.springjwt.repository.PanierRepository;
import com.apiRegion.springjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PanierServiceImpl implements PanierService {
    @Autowired
    private PanierRepository panierRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public ReponseMessage Ajouter(Panier panier, Produit produit, User user) {
        Panier  panier2 = panierRepository.findByProduitsAndUser(produit, user);
        Boolean panier3 = panierRepository.existsByProduitsAndUser(produit,user);

        if(!panier3){
            panier.setUser(user);
            panier.setQuantite(panier.getQuantite());
            panier.setTotalproduit((produit.getPrix()));
            panierRepository.save(panier);
            ReponseMessage message = new ReponseMessage("Produit ajouté au panier", true);
            return message;
        }
        else {
            panier2.setTotalproduit(panier2.getTotalproduit() + (produit.getPrix()*panier.getQuantite()));
            panier2.setQuantite(panier2.getQuantite() + panier.getQuantite());
            panierRepository.save(panier2);
            ReponseMessage message = new ReponseMessage("Produit ajouté au panier",true);
            return message;
        }
}

    @Override
    public ReponseMessage Supprimer(Panier panier, Produit produit, Long user) {

        Optional<User> user1 = userRepository.findById(user);
        Panier  panier1 = panierRepository.findByProduitsAndUser(produit, user1.get());
        Boolean panier2 = panierRepository.existsByProduitsAndUser(produit,user1.get());

        panierRepository.delete(panier1);
        ReponseMessage message = new ReponseMessage("Produit supprimer du panier",true);
        return message;
    }
}
