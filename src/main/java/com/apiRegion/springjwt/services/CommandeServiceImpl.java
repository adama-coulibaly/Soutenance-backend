package com.apiRegion.springjwt.services;

import com.apiRegion.springjwt.Message.ReponseMessage;
import com.apiRegion.springjwt.models.*;
import com.apiRegion.springjwt.repository.*;
import com.apiRegion.springjwt.security.EmailConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class CommandeServiceImpl implements CommandeService {
    @Autowired
    private HistoriqueRepository historiqueRepository;
    private final PanierRepository panierRepository;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private EmailConstructor emailConstructor;
    @Autowired
    private CommandeRepository commandeRepository;
    private final ProduitRepository produitRepository;
    private final ProductionRepository productionRepository;

    public CommandeServiceImpl(PanierRepository panierRepository,
                               ProduitRepository produitRepository,
                               ProductionRepository productionRepository) {
        this.panierRepository = panierRepository;
        this.produitRepository = produitRepository;
        this.productionRepository = productionRepository;
    }

    @Override
    public ReponseMessage ajouter(Commande commande, User user) {

        Long Difference = 0L;

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
               Optional<Produit> produit = produitRepository.findById(panier1.getProduits().get(0).getIdproduit());

               // ========================================= ICI ON AJOUTE HISTORIQUE DES VENTES
               Historique historique = new Historique();
               historique.setNomproduit(panier1.getProduits().get(0).getNomproduit());
               historique.setNomclient(user.getNom());
               historique.setPrenomclient(user.getPrenom());
               historique.setNumeroclient(user.getUsername());
               historique.setDatevente(new Date());
               historique.setFerme(panier1.getProduits().get(0).getFermes().get(0));
               historique.setQuantite(panier1.getQuantite());
               historique.setPrixunitaire(panier1.getProduits().get(0).getPrix());
               historique.setMontanttotal((panier1.getProduits().get(0).getPrix()) * (panier1.getQuantite()));
               historiqueRepository.save(historique);


             // commande.setPaniers(panier1);

              TotalQ += (panier1.getQuantite());
              Totaux+=panier1.getTotalproduit();
              commande.setMontanttotal(Totaux);
              commande.setQuantitecommande(TotalQ);
              commande.setCodecommande(codeCommende+r.nextInt(100));
              panier1.getProduits();
              panier1.setEtat(false);
               Difference = (panier1.getProduits().get(0).getQuantiteVente() - commande.getQuantitecommande());
               produit.get().setQuantiteVente(Difference);

           }

            // ========================================= ICI ON ATTRIBUT LES VALEURS DE USER QUI PASSE LA COMMANDE
            commandeRepository.save(commande);

          mailSender.send(emailConstructor.sendMailCommande(user,commande)); // SEND EMAIL
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
