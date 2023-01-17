package com.apiRegion.springjwt.services;

import com.apiRegion.springjwt.Message.ReponseMessage;
import com.apiRegion.springjwt.models.Commande;
import com.apiRegion.springjwt.models.Panier;
import com.apiRegion.springjwt.models.User;
import com.apiRegion.springjwt.repository.PanierRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CommandeServiceImpl implements CommandeService {
    private final PanierRepository panierRepository;

    public CommandeServiceImpl(PanierRepository panierRepository) {
        this.panierRepository = panierRepository;
    }

    @Override
    public ReponseMessage ajouter(Commande commande, User user) {
        List<Panier> user1 = panierRepository.findByUser(user);
        if (user1 != null){
            System.out.println("Utilisateur existe biensure");
        }
        else{
            commande.setUser(user);
            commande.setDatecommande(LocalDate.now());
           // commande.getPaniers().add(user1);
            System.out.println("Utilisateur existe biensure");
            return null;
        }
        return null;

    }
}
