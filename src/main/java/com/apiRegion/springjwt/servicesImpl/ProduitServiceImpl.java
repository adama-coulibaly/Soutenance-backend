package com.apiRegion.springjwt.servicesImpl;

import com.apiRegion.springjwt.Message.ReponseMessage;
import com.apiRegion.springjwt.models.Ferme;
import com.apiRegion.springjwt.models.Produit;
import com.apiRegion.springjwt.models.User;
import com.apiRegion.springjwt.repository.ProduitRepository;
import com.apiRegion.springjwt.services.ProduitService;
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
            return new ReponseMessage("Produit créer avec succès",true);
        }
        else{
            return new ReponseMessage("Cette reference de prodits existe déjàs  existe déjà !",false);
        }
    }

    @Override
    public ReponseMessage Modifier(Produit produit, Long idproduit) {
        Optional<Produit> produit1 = produitRepository.findById(idproduit);
        if(!produit1.isPresent()){

            return new ReponseMessage("Ce produit n'est pas trouvée !", false);
        }
        else {

            Produit produit2 = produitRepository.findById(idproduit).get();
            produit2.setDescriptionproduit(produit.getDescriptionproduit());
            produit2.setNomproduit(produit.getNomproduit());
            produit2.setReference(produit.getReference());
            produit2.setEtat(produit.isEtat());
            produit2.setPhtoproduit(produit.getPhtoproduit());
            produit2.setPrix(produit.getPrix());

            this.produitRepository.save(produit2);

            return new ReponseMessage("Produit modifiée avec succès !", true);


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
            return new ReponseMessage("Etat modifiée avec succès !", true);
        }
        else {
            return new ReponseMessage("Produit non modifiés !e", false);


        }
    }

    @Override
    public List<Produit> Lister() {
        return produitRepository.findAll();
    }
}
