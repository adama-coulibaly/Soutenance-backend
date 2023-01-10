package com.apiRegion.springjwt.services;

import com.apiRegion.springjwt.Message.ReponseMessage;
import com.apiRegion.springjwt.models.Ferme;
import com.apiRegion.springjwt.models.Produit;
import com.apiRegion.springjwt.models.User;
import com.apiRegion.springjwt.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProduitServiceImpl implements ProduitService {


    @Autowired
    private ProduitRepository produitRepository;
    @Override
    public ReponseMessage Ajouter(Produit produit, Ferme ferme) {

        if(produitRepository.findByReference(produit.getReference()) == null){
          produit.getFermes().add(ferme);

            produitRepository.save(produit);
            ReponseMessage message = new ReponseMessage("Produit créer avec succès",true);
            return message;
        }
        else{
            ReponseMessage message = new ReponseMessage("Cette reference de prodits existe déjàs  existe déjà !",false);
            return message;
        }
    }

    @Override
    public ReponseMessage Modifier(Produit produit, Long idproduit) {
        Optional<Produit> produit1 = produitRepository.findById(idproduit);
        if(!produit1.isPresent()){

            ReponseMessage message = new ReponseMessage("Ce produit n'est pas trouvée !", false);
            return message;
        }
        else {

            Produit produit2 = produitRepository.findById(idproduit).get();
            produit2.setDescriptionproduit(produit.getDescriptionproduit());
            produit2.setNomproduit(produit.getNomproduit());
            produit2.setReference(produit.getReference());
            produit2.setEtat(produit.isEtat());

            this.produitRepository.save(produit2);

            ReponseMessage message = new ReponseMessage("Ferme modifiée avec succès !", true);
            return message;


        }
    }

    @Override
    public ReponseMessage Supprimer(Long idproduit) {
        return null;
    }

    @Override
    public ReponseMessage SetEtat(Produit produit, Long idproduit) {
        Optional<Produit> produit1 = produitRepository.findById(idproduit);
        if(produit1.isPresent()){
            Produit produit2 = produitRepository.findById(idproduit).get();
            produit2.setEtat(produit.isEtat());
            this.produitRepository.save(produit2);
            ReponseMessage message = new ReponseMessage("Etat modifiée avec succès !", true);
            return message;
        }
        else {
            ReponseMessage message = new ReponseMessage("Produit non modifiés !e", false);
            return message;


        }
    }

    @Override
    public List<Produit> Lister() {
        return produitRepository.findAll();
    }
}
