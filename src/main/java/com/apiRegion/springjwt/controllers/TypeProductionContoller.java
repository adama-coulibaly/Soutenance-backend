package com.apiRegion.springjwt.controllers;

import com.apiRegion.springjwt.Message.ReponseMessage;
import com.apiRegion.springjwt.models.Ferme;
import com.apiRegion.springjwt.models.Typeproduction;
import com.apiRegion.springjwt.repository.TypeProdRepository;
import com.apiRegion.springjwt.services.TypeProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/typeproduction")
public class TypeProductionContoller {

    @Autowired
    private TypeProductionService typeProductionService;

    @Autowired
    private TypeProdRepository typeProdRepository;
//============================================== AJOUT DES TYPES DE PRODUCTIONS
    @PostMapping("/ajouter")
    public ReponseMessage ajouter(@RequestBody Typeproduction typeproduction){
        return this.typeProductionService.Ajouter(typeproduction);
    }

    //============================================== ON LISTE LES TYPES DE PRODUCTIONS

    @GetMapping("/liste")
    public List<Typeproduction> lesProductions(){
        return this.typeProductionService.Lister();
    }

    //============================================== ON SUPPRIME UN TYPE DE PRODUCTION

    @DeleteMapping("supprimer/{idtype}")
    public ReponseMessage changeEtat(@PathVariable("idtype") Long idtype)
    {

        Optional<Typeproduction> typeproduction = this.typeProdRepository.findById(idtype);
        if (!typeproduction.isPresent())
        {
            ReponseMessage message = new ReponseMessage("Type de production non trouvée !", false);
            return message;
        }
        else {
            this.typeProdRepository.delete(typeproduction.get());
            ReponseMessage message = new ReponseMessage("Type de production supprimé avec succès !", true);
            return message;
        }

    }
    //============================================== ON MODIFIE UN TYPE DE PRODUCTION

    @PutMapping("/modifier/{idtype}")
        public ReponseMessage modifier(@RequestBody Typeproduction typeproduction, @PathVariable("idtype") Long idtype){

            if(typeProdRepository.findById(idtype) != null){

                this.typeProductionService.Modifier(typeproduction,idtype);
                ReponseMessage message = new ReponseMessage("Type modifier avec succès !", true);
                return message;
            }
            else{
                ReponseMessage message = new ReponseMessage("Type de production non trouvé !", false);
                return message;
            }
    }

}
