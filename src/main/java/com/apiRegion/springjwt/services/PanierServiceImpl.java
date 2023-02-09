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
        System.out.println("Test Serv = "+panier);

        Boolean panier4 = panierRepository.existsByProduitsAndUserAndEtat(produit,user,true);
        Panier pa = panierRepository.findByProduitsAndUserAndEtat(produit,user,true);
        System.out.println("Test Serv avant = "+panier);

        if(!panier4 ){
            System.out.println("Test Serv if = "+panier);

            System.out.println("Je suis 1 "+panier4);
            panier.setUser(user);
            panier.setQuantite(panier.getQuantite());
            panier.setTotalproduit((produit.getPrix()));
            panier.setEtat(true);
            panierRepository.save(panier);
            ReponseMessage message = new ReponseMessage("Produit ajouté au panier", true);
            return message;
        }
        else {
            System.out.println("Je suis 2 "+panier4+" "+pa);
            pa.setTotalproduit(pa.getTotalproduit() + (produit.getPrix()*panier.getQuantite()));
            pa.setQuantite(pa.getQuantite() + panier.getQuantite());
            panierRepository.save(pa);
            ReponseMessage message = new ReponseMessage("Produit ajouté au panier",true);
            return message;
        }


        /*if(!panier3 ){
            System.out.println("Je suis 1 "+panier3);
            panier.setUser(user);
            panier.setQuantite(panier.getQuantite());
            panier.setTotalproduit((produit.getPrix()));
            panier.setEtat(true);
            panierRepository.save(panier);
            ReponseMessage message = new ReponseMessage("Produit ajouté au panier", true);
            return message;
        }
        else {
            System.out.println("Je suis 2 "+panier3+" "+panier1);
            panier1.setTotalproduit(panier1.getTotalproduit() + (produit.getPrix()*panier.getQuantite()));
            panier1.setQuantite(panier1.getQuantite() + panier.getQuantite());
               panierRepository.save(panier1);
               ReponseMessage message = new ReponseMessage("Produit ajouté au panier",true);
               return message;
           }*/

    }

    @Override
    public ReponseMessage Modifier(Panier panier, Produit produit, User user) {
        Boolean panier4 = panierRepository.existsByProduitsAndUserAndEtat(produit,user,true);
        Panier pa = panierRepository.findByProduitsAndUserAndEtat(produit,user,true);

        if(!panier4 ){
            System.out.println("Je suis 1 "+panier4);
            panier.setUser(user);
            panier.setQuantite(panier.getQuantite());
            panier.setTotalproduit((produit.getPrix()));
            panier.setEtat(true);
            panierRepository.save(panier);
            ReponseMessage message = new ReponseMessage("Produit ajouté au panier", true);
            return message;
        }
        else {
            System.out.println("Je suis 2 "+panier4+" "+pa);
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
       /* Panier  panier1 = panierRepository.findByProduitsAndUser(produit, user1.get());
        Boolean panier2 = panierRepository.existsByProduitsAndUser(produit,user1.get());*/


       // Boolean panier4 = panierRepository.existsByProduitsAndUserAndEtat(produit,user1,true);
        Panier pa = panierRepository.findByProduitsAndUserAndEtat(produit,user1,true);

        panierRepository.delete(pa);
        ReponseMessage message = new ReponseMessage("Produit supprimer du panier",true);
        return message;
    }
}
