package com.apiRegion.springjwt.services;

import com.apiRegion.springjwt.Message.ReponseMessage;
import com.apiRegion.springjwt.models.Commande;
import com.apiRegion.springjwt.models.Panier;
import com.apiRegion.springjwt.models.Produit;
import com.apiRegion.springjwt.models.User;
import com.apiRegion.springjwt.repository.CommandeRepository;
import com.apiRegion.springjwt.repository.PanierRepository;
import com.apiRegion.springjwt.repository.ProduitRepository;
import com.apiRegion.springjwt.security.EmailConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class CommandeServiceImpl implements CommandeService {
    private final PanierRepository panierRepository;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private EmailConstructor emailConstructor;
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

        List<Panier> panier = panierRepository.findByUserAndEtat(user,true);
        commande.setDatecommande(LocalDate.now());
        commande.setUser(user);
        commande.setStatus("en cours");

        Produit p = new Produit();



        Long Totaux = 0l;
        Long TotalQ = 0l;
        if (panier.size() != 0 ){
           for(Panier panier1:panier){

             // commande.setPaniers(panier1);

              TotalQ += (panier1.getQuantite());
              Totaux+=panier1.getTotalproduit();
              commande.setMontanttotal(Totaux);
              commande.setQuantitecommande(TotalQ);
              commande.setCodecommande(codeCommende+r.nextInt(100));
              panier1.getProduits();
              panier1.setEtat(false);

               System.out.println("Email des fermiers: "+panier1.getProduits().get(0).getFermes().get(0).getUser().getEmail());
               System.out.println("Produits concernés: "+panier1.getProduits().get(0).getNomproduit());
               System.out.println("Code produits : "+panier1.getProduits().get(0).getReference());
               System.out.println("PanierQ : "+panier1.getProduits().get(0).getQuantiteVente());
               System.out.println("Difference : "+(panier1.getProduits().get(0).getQuantiteVente()-panier1.getQuantite()));


           }
           System.out.println("Utilisateur "+user.getNom());
            commandeRepository.save(commande);
                    //mailSender.send(emailConstructor.sendMailCommande(user,commande)); // SEND EMAIL
           ReponseMessage message = new ReponseMessage("Commende effectuée avec succès",true);
           return message;

        }
        else{
            commande.setUser(user);
            commande.setDatecommande(LocalDate.now());
            System.out.println("Votre panier est vide !");
            return null;
        }


    }
}
