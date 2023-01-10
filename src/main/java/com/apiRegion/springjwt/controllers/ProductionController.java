package com.apiRegion.springjwt.controllers;

import com.apiRegion.springjwt.Message.ReponseMessage;
import com.apiRegion.springjwt.models.Ferme;
import com.apiRegion.springjwt.models.Production;
import com.apiRegion.springjwt.models.Typeproduction;
import com.apiRegion.springjwt.repository.FermeRepository;
import com.apiRegion.springjwt.repository.ProductionRepository;
import com.apiRegion.springjwt.services.ProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Period;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/production")
public class ProductionController {
    @Autowired
    private ProductionService productionService;

    @Autowired
    private ProductionRepository productionRepository;
    @Autowired
    private FermeRepository fermeRepository;

    @PostMapping("/ajouter/{idtypeproduction}/{ferme}")
    public ReponseMessage ajouter(@RequestBody Production production, @PathVariable("idtypeproduction")Typeproduction typeproduction, @PathVariable("ferme") Ferme ferme){
        production.setTypeproduction(typeproduction);
        // CETTE VARIABLE VAS NOUS PERMETTRE DE CREER UN INTERVALE DE TEMPS
        Period intervalle = Period.between(production.getDateentrer(),production.getDatesortie());
        
        List<Production> production1 = productionRepository.findAll();
        if(production.getDateentrer().isAfter(production.getDatesortie())){
            ReponseMessage message = new ReponseMessage("La date de sortie doit êtres superieur à la date d'entrée !", false);
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
                    if(production1 != null){
                        this.productionService.Ajouter(production,typeproduction,ferme);
                        ReponseMessage message = new ReponseMessage("Production ajoutée avec succès  !", true);

                        return message;
                    }
                    else{
                        ReponseMessage message = new ReponseMessage("Production existe !", false);
                       // System.out.println("================  "+ferme);
                        return message;
                    }



            }


}
    @PatchMapping("/modifier/{idproduction}")
    public ReponseMessage Modifier(@RequestBody Production production,@PathVariable("idproduction") Long idproduction){

        Period intervalle = Period.between(production.getDateentrer(),production.getDatesortie());

        if(this.productionRepository.findById(idproduction) == null){

            ReponseMessage message = new ReponseMessage("Production n'existe pas !", false);
            return message;
        }
        else{

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

            }   this.productionService.Modifier(production,idproduction);
            ReponseMessage message = new ReponseMessage("Production modifier avec succsès !", true);
            return message;

        }



    }









    @PatchMapping("/etat/{idproduction}")
    public ReponseMessage SetEtat(@RequestBody Production production,@PathVariable("idproduction") Long idproduction){
        if(this.productionRepository.findById(idproduction) == null){

            ReponseMessage message = new ReponseMessage("Production n'existe pas !", false);
            return message;
        }
        else{

            this.productionService.SetEtat(production,idproduction);
            ReponseMessage message = new ReponseMessage("Etat modifier avec succsès !", true);
            return message;
    }
}



    }



