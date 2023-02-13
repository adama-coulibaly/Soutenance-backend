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
        Boolean panier4 = panierRepository.existsByProduitsAndUserAndEtat(produit,user,true);
        Panier pa = panierRepository.findByProduitsAndUserAndEtat(produit,user,true);

        if(!panier4 ){
            panier.setUser(user);
            panier.setQuantite(panier.getQuantite());
            panier.setTotalproduit((produit.getPrix()));
            panier.setEtat(true);
            panierRepository.save(panier);
            ReponseMessage message = new ReponseMessage("Produit ajouté au panier", true);
            return message;
        }
        else {
            pa.setTotalproduit(pa.getTotalproduit() + (produit.getPrix()*panier.getQuantite()));
            pa.setQuantite(pa.getQuantite() + panier.getQuantite());
            panierRepository.save(pa);
            ReponseMessage message = new ReponseMessage("Produit ajouté au panier",true);
            return message;
        }
    }

    @Override
    public ReponseMessage Modifier(Panier panier, Produit produit, User user) {
        Boolean panier4 = panierRepository.existsByProduitsAndUserAndEtat(produit,user,true);
        Panier pa = panierRepository.findByProduitsAndUserAndEtat(produit,user,true);

        if(!panier4 ){
            panier.setUser(user);
            panier.setQuantite(panier.getQuantite());
            panier.setTotalproduit((produit.getPrix()));
            panier.setEtat(true);
            panierRepository.save(panier);
            ReponseMessage message = new ReponseMessage("Produit ajouté au panier", true);
            return message;
        }
        else {
            pa.setTotalproduit((produit.getPrix()*panier.getQuantite()));
            pa.setQuantite(panier.getQuantite());
            panierRepository.save(pa);
            ReponseMessage message = new ReponseMessage("Produit ajouté au panier",true);
            return message;
        }
    }

    @Override
    public ReponseMessage Supprimer(Panier panier, Produit produit, Long user) {

        User user1 = userRepository.findById(user).get();
        Panier pa = panierRepository.findByProduitsAndUserAndEtat(produit,user1,true);

        panierRepository.delete(pa);
        ReponseMessage message = new ReponseMessage("Produit supprimer du panier",true);
        return message;
    }
}
