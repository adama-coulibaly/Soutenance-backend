package com.apiRegion.springjwt.services;

import com.apiRegion.springjwt.Message.ReponseMessage;
import com.apiRegion.springjwt.models.Panier;
import com.apiRegion.springjwt.models.Produit;
import com.apiRegion.springjwt.models.User;
import com.apiRegion.springjwt.repository.PanierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PanierServiceImpl implements PanierService {
    @Autowired
    private PanierRepository panierRepository;

    @Override
    public ReponseMessage Ajouter(Panier panier, Produit produit, User user) {

      /*  Optional<Panier>  panier1 = panierRepository.findByProduits(produit);
       if(!panier1.isPresent()){

       */
            panier.setUser(user);
            panier.setQuantite(panier.getQuantite());
            panier.setTotalproduit((produit.getPrix()));
            panierRepository.save(panier);
            ReponseMessage message = new ReponseMessage("Produit ajouté au panier", true);
            return message;
     /*  }
        else {
            Optional<Panier> p = panierRepository.findByUser(user);
            p.get().setTotalproduit(p.get().getTotalproduit() + (produit.getPrix()*panier.getQuantite()));
              //  p.get().(p.getTotalproduit() + (produit.getPrix() * panier.getQuantite()));
             //   p.setQuantite(p.getQuantite() + panier.getQuantite());
                p.get().setQuantite(p.get().getQuantite() + panier.getQuantite());
             //   p.getProduits().add(produit);

                panierRepository.save(p.get());
                ReponseMessage message = new ReponseMessage("Produit ajouté au panier",true);
                return message;
            }

      */

}
}
