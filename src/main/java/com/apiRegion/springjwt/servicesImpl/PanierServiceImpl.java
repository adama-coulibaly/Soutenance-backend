package com.apiRegion.springjwt.servicesImpl;

import com.apiRegion.springjwt.Message.ReponseMessage;
import com.apiRegion.springjwt.models.Panier;
import com.apiRegion.springjwt.models.Produit;
import com.apiRegion.springjwt.models.User;
import com.apiRegion.springjwt.repository.PanierRepository;
import com.apiRegion.springjwt.repository.UserRepository;
import com.apiRegion.springjwt.services.PanierService;
import com.google.firebase.database.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
public class PanierServiceImpl implements PanierService {

    private final PanierRepository panierRepository;

    private final UserRepository userRepository;

    public PanierServiceImpl(PanierRepository panierRepository, UserRepository userRepository) {
        this.panierRepository = panierRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ReponseMessage Ajouter(Panier panier, Produit produit, User user) {
        Boolean panier4 = panierRepository.existsByProduitsAndUserAndEtat(produit, user, true);
        Panier pa = panierRepository.findByProduitsAndUserAndEtat(produit, user, true);

        if (!panier4) {
            return getReponseMessage(panier, produit, user);
        } else {
            pa.setTotalproduit(pa.getTotalproduit() + (produit.getPrix() * panier.getQuantite()));
            pa.setQuantite(pa.getQuantite() + panier.getQuantite());
            panierRepository.save(pa);
            return new ReponseMessage("Produit ajouté au panier", true);
        }
    }

    @Override
    public ReponseMessage Modifier(Panier panier, Produit produit, User user) {
        Boolean panier4 = panierRepository.existsByProduitsAndUserAndEtat(produit, user, true);
        Panier pa = panierRepository.findByProduitsAndUserAndEtat(produit, user, true);

        if (!panier4) {
            return getReponseMessage(panier, produit, user);
        } else {
            pa.setTotalproduit((produit.getPrix() * panier.getQuantite()));
            pa.setQuantite(panier.getQuantite());
            panierRepository.save(pa);
            return new ReponseMessage("Produit ajouté au panier", true);
        }
    }

    @NotNull
    private ReponseMessage getReponseMessage(Panier panier, Produit produit, User user) {
        panier.setUser(user);
        panier.setQuantite(panier.getQuantite());
        panier.setTotalproduit((produit.getPrix()));
        panier.setEtat(true);
        panierRepository.save(panier);
        return new ReponseMessage("Produit ajouté au panier", true);
    }

    @Override
    public ReponseMessage Supprimer(Panier panier, Produit produit, Long user) {

        User user1 = userRepository.findById(user).get();
        Panier pa = panierRepository.findByProduitsAndUserAndEtat(produit, user1, true);

        panierRepository.delete(pa);
        return new ReponseMessage("Produit supprimer du panier", true);
    }
}
