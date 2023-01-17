package com.apiRegion.springjwt.services;

import com.apiRegion.springjwt.Message.ReponseMessage;
import com.apiRegion.springjwt.models.Commande;
import com.apiRegion.springjwt.models.Panier;
import com.apiRegion.springjwt.models.Produit;
import com.apiRegion.springjwt.models.User;
import com.apiRegion.springjwt.repository.CommandeRepository;
import com.apiRegion.springjwt.repository.PanierRepository;
import com.apiRegion.springjwt.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class CommandeServiceImpl implements CommandeService {
    private final PanierRepository panierRepository;

    @Autowired
    private CommandeRepository commandeRepository;
    private final ProduitRepository produitRepository;

    public CommandeServiceImpl(PanierRepository panierRepository,
                               ProduitRepository produitRepository) {
        this.panierRepository = panierRepository;
        this.produitRepository = produitRepository;
    }

    @Override
    public ReponseMessage ajouter(Commande commande, User user) {
        String codeCommende = "CO"+user.getNom();
        Random r = new Random(1000);

        List<Panier> panier = panierRepository.findByUser(user);
        commande.setDatecommande(LocalDate.now());
        commande.setUser(user);
        commande.setStatus("en cours");

Produit p = new Produit();



        Long Totaux = 0l;
        Long TotalQ = 0l;
        if (panier.size() != 0){
           for(Panier panier1:panier){

              commande.setPaniers(panier1);
              TotalQ += (panier1.getQuantite());
              Totaux+=panier1.getTotalproduit();
              commande.setMontanttotal(Totaux);
              commande.setQuantitecommande(TotalQ);
              commande.setCodecommande(codeCommende+r.nextInt(100));
              panier1.getProduits();

               System.out.println("Mes prod : "+panier1.getProduits() );
           }
  //  System.out.println("Mes prod : "+);
            commandeRepository.save(commande);

        }
        else{
            commande.setUser(user);
            commande.setDatecommande(LocalDate.now());
            System.out.println("Utilisateur existe biensure");
            return null;
        }
        return null;

    }
}
