package com.apiRegion.springjwt.services;

import com.apiRegion.springjwt.Message.ReponseMessage;
import com.apiRegion.springjwt.models.Ferme;
import com.apiRegion.springjwt.models.Produit;
import com.apiRegion.springjwt.models.User;
import com.apiRegion.springjwt.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProduitServiceImpl implements ProduitService {


    @Autowired
    private ProduitRepository produitRepository;
    @Override
    public ReponseMessage Ajouter(Produit produit, Ferme ferme) {

        if(produitRepository.findByReference(produit.getReference()) == null){
          //  produit.getFermes().add(ferme);

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
    public ReponseMessage Modifier(Produit produit, Long idferme) {
        return null;
    }

    @Override
    public ReponseMessage Supprimer(Long idproduit) {
        return null;
    }

    @Override
    public ReponseMessage SetEtat(Produit produit, Long idferme) {
        return null;
    }

    @Override
    public List<Produit> Lister() {
        return null;
    }
}
