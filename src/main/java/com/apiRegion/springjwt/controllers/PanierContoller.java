package com.apiRegion.springjwt.controllers;

import com.apiRegion.springjwt.Message.ReponseMessage;
import com.apiRegion.springjwt.models.Panier;
import com.apiRegion.springjwt.models.Produit;
import com.apiRegion.springjwt.models.User;
import com.apiRegion.springjwt.repository.PanierRepository;
import com.apiRegion.springjwt.repository.UserRepository;
import com.apiRegion.springjwt.services.PanierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/panier")
public class PanierContoller {
    @Autowired
    private PanierService panierService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PanierRepository panierRepository;

    @PostMapping("/ajouter/{produit}/{user}")
    public ReponseMessage Ajouter(@RequestBody Panier panier, @PathVariable("produit") Produit produit, @PathVariable("user") User user) {

       if(userRepository.findById(user.getId()) != null) {
           Long Qte =  (panier.getQuantite());
           panier.setUser(user);
           panier.setQuantite(1L);
           panier.setTotalproduit((produit.getPrix()));
           panier.getProduits().add(produit);

           return  panierService.Ajouter(panier,produit,user);
       }else {
           ReponseMessage message = new ReponseMessage("Impossible d'ajouté au panier",false);
           return message;
       }
}

    @PostMapping("/modifierquantite/{produit}/{user}")
    public ReponseMessage AjouterModifier(@RequestBody Panier panier, @PathVariable("produit") Produit produit, @PathVariable("user") User user) {

        if(userRepository.findById(user.getId()) != null) {
            Long Qte =  (panier.getQuantite());
            panier.setUser(user);
            panier.setQuantite(panier.getQuantite());
            panier.setTotalproduit((produit.getPrix()));
            panier.getProduits().add(produit);
            return  panierService.Ajouter(panier,produit,user);
        }else {
            ReponseMessage message = new ReponseMessage("Impossible d'ajouté au panier",false);
            return message;
        }
    }








    @GetMapping("/panierParUser/{users}")
    public Optional<Panier> panierParUsers(@PathVariable("users") User user){
        return panierRepository.findByUser(user);

    }
}
