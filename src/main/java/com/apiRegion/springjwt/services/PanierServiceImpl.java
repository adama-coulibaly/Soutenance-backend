package com.apiRegion.springjwt.services;

import com.apiRegion.springjwt.Message.ReponseMessage;
import com.apiRegion.springjwt.models.Panier;
import com.apiRegion.springjwt.models.Produit;
import com.apiRegion.springjwt.models.User;
import com.apiRegion.springjwt.repository.PanierRepository;
import com.apiRegion.springjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PanierServiceImpl implements PanierService {
    @Autowired
    private PanierRepository panierRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public ReponseMessage Ajouter(Panier panier, Produit produit, User user) {
        Boolean panier3 = panierRepository.existsByProduitsAndUserAndEtat(produit,user,true);


        if(!panier3 ){
            panier.setUser(user);
            panier.setQuantite(panier.getQuantite());
            panier.setTotalproduit((produit.getPrix()));
            panier.setEtat(true);
            panierRepository.save(panier);
            ReponseMessage message = new ReponseMessage("Produit ajouté au panier", true);
            return message;
        }
        else {

            panier.setTotalproduit(panier.getTotalproduit() + (produit.getPrix()*panier.getQuantite()));
            panier.setQuantite(panier.getQuantite() + panier.getQuantite());
               panierRepository.save(panier);
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
