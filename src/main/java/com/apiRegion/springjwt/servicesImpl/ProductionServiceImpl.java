package com.apiRegion.springjwt.servicesImpl;

import com.apiRegion.springjwt.Message.ReponseMessage;
import com.apiRegion.springjwt.models.*;
import com.apiRegion.springjwt.repository.ProductionRepository;
import com.apiRegion.springjwt.services.ProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class ProductionServiceImpl implements ProductionService {

    private final ProductionRepository productionRepository;

    public ProductionServiceImpl(ProductionRepository productionRepository) {
        this.productionRepository = productionRepository;
    }

    @Override
    public ReponseMessage Ajouter(Production production, Typeproduction typeproduction, Ferme ferme) {

    List<Production> typeprodeFerme = productionRepository.findByDateentrerAndDatesortieAndFerme(production.getDateentrer(),production.getDatesortie(),ferme);
       if(typeprodeFerme.size() == 0){

                production.setTypeproduction(typeproduction);
                production.setFerme(ferme);
                productionRepository.save(production);
           return new ReponseMessage("Production ajoutée avec succès !", true);
            }
            else {
           return new ReponseMessage("Cette production existe !", false);}
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

            if(production.getDateentrer() != null){
                production2.setDateentrer(production.getDateentrer());
            }
            else{
                production2.setDateentrer(production2.getDateentrer());
            }

            if(production.getDatesortie() != null){
                production2.setDatesortie(production.getDatesortie());
            }
            else{
                production2.setDatesortie(production2.getDatesortie());
            }

            if(production.getQuantite() != 0){
                production2.setQuantite(production.getQuantite());
            }
            else{
                production2.setQuantite(production2.getQuantite());
            }
            if(production.getTypeproduction() != null){
                production2.setTypeproduction(production.getTypeproduction());
            }
            else{
                production2.setTypeproduction(production2.getTypeproduction());
            }

           if(production.getDateentrer().isAfter(production.getDatesortie())){
               return new ReponseMessage("Veuillez donnez une date correcte !", false);
           }
           else if(production.getDateentrer().equals(production.getDatesortie())){
               return new ReponseMessage("Veuillez donner un interval de date correct !", false);
           } else if (intervalle.getMonths() < 1 ) {

               return new ReponseMessage("Veuillez donner un interval superieur a moins 30 jours !", false);
           }
           else {
               production2.setStatus(production.getStatus());
               this.productionRepository.save(production2);
               return new ReponseMessage("Production modifiée avec succès !", true);
           }


        }

    }

    @Override
    public ReponseMessage setStatus(Production production, Long idproduction) {
        Production p = productionRepository.findById(idproduction).get();
        p.setStatus(production.getStatus());
        this.productionRepository.save(production);
        return new ReponseMessage("Status modifiée avec succès !", true);
    }

    @Override
    public ReponseMessage SetEtat(Production production, Long idproduction) {
        Optional<Production> production1 = productionRepository.findById(idproduction);
        if(production1.isPresent()){
            Production production2 = productionRepository.findById(idproduction).get();
            production2.setEtat(production.isEtat());
            this.productionRepository.save(production2);
            return new ReponseMessage("Production modifiée avec succès !", true);
        }
        else {
            return new ReponseMessage("Production non avec succès !", false);

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
