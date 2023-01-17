package com.apiRegion.springjwt.services;

import com.apiRegion.springjwt.Message.ReponseMessage;
import com.apiRegion.springjwt.models.Ferme;
import com.apiRegion.springjwt.models.Production;
import com.apiRegion.springjwt.models.Theme;
import com.apiRegion.springjwt.models.Typeproduction;
import com.apiRegion.springjwt.repository.ProductionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class ProductionServiceImpl implements ProductionService {

    @Autowired
    private ProductionRepository productionRepository;
    @Override
    public ReponseMessage Ajouter(Production production, Typeproduction typeproduction, Ferme ferme) {

      //  Boolean dates = productionRepository.existsByDateentrerAndDatesortie(production.getDateentrer(),production.getDatesortie());
        Boolean typeprodeFerme = productionRepository.existsByFermeAndTypeproduction(ferme,typeproduction);

        if(typeprodeFerme){
            production.setTypeproduction(typeproduction);
            production.getFerme().add(ferme);
            productionRepository.save(production);
            ReponseMessage message = new ReponseMessage("Production ajoutée avec succès !", true);
            return message;
        }
        else{
            ReponseMessage message = new ReponseMessage("Production existe déjà !", false);
            return message;
        }



    }

    @Override
    public ReponseMessage Modifier(Production production, Long id) {
        Optional<Production> production1 = productionRepository.findById(id);
        if(!production1.isPresent()){

            ReponseMessage message = new ReponseMessage("Cette production n'est pas trouvée !", false);
            return message;
        }
        else {
            Period intervalle = Period.between(production.getDateentrer(),production.getDatesortie());
            Production production2 = productionRepository.findById(id).get();
           production2.setDatesortie(production.getDatesortie());
           production2.setDateentrer(production.getDateentrer());
           production2.setQuantite(production.getQuantite());
           production2.setTypeproduction(production.getTypeproduction());
           production2.setEtat(production.isEtat());

           if(production.getDateentrer().isAfter(production.getDatesortie())){
               ReponseMessage message = new ReponseMessage("Veuillez donnez une date correcte !", false);
               return message;
           }
           else if(production.getDateentrer().equals(production.getDatesortie())){
               ReponseMessage message = new ReponseMessage("Veuillez donner un interval de date correct !", false);
               return message;
           } else if (intervalle.getMonths() < 1 ) {

               ReponseMessage message = new ReponseMessage("Veuillez donner un interval superieur a moins 30 jours !", false);
               return message;
           }
           else {
               this.productionRepository.save(production2);
               ReponseMessage message = new ReponseMessage("Production modifiée avec succès !", true);
               return message;
           }


        }

    }

    @Override
    public ReponseMessage SetEtat(Production production, Long idproduction) {
        Optional<Production> production1 = productionRepository.findById(idproduction);
        if(production1.isPresent()){
            Production production2 = productionRepository.findById(idproduction).get();
            production2.setEtat(production.isEtat());
            this.productionRepository.save(production2);
            ReponseMessage message = new ReponseMessage("Production modifiée avec succès !", true);
            return message;
        }
        else {
            ReponseMessage message = new ReponseMessage("Production non avec succès !", false);
            return message;

        }
    }

    @Override
    public ReponseMessage Supprimer(Long idproduit) {
        return null;
    }

    @Override
    public List<Production> Lister() {
        return productionRepository.findAll();
    }
}
