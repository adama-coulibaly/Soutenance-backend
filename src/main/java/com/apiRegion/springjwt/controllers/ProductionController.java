package com.apiRegion.springjwt.controllers;

import com.apiRegion.springjwt.Message.ReponseMessage;
import com.apiRegion.springjwt.models.Ferme;
import com.apiRegion.springjwt.models.Production;
import com.apiRegion.springjwt.models.Typeproduction;
import com.apiRegion.springjwt.repository.ProductionRepository;
import com.apiRegion.springjwt.services.ProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/production")
public class ProductionController {
    @Autowired
    private ProductionService productionService;

    @Autowired
    private ProductionRepository productionRepository;

    @PostMapping("/ajouter/{idtypeproduction}/{idferme}")
    public ReponseMessage ajouter(@RequestBody Production production, @PathVariable("idtypeproduction")Typeproduction typeproduction, @PathVariable("idferme") Ferme ferme){
       // production.setTypeproduction((List<Typeproduction>) typeproduction);
        production.getFerme().add(ferme);


      //  if(productionRepository.findByDateentrer(production.getDateentrer()) == null | productionRepository.findByDatesortie(production.getDatesortie()) == null ){
             this.productionService.Ajouter(production,typeproduction,ferme);
            ReponseMessage message = new ReponseMessage("Production ajoutée avec succès !", true);
            return message;
      /*  }else {
        ReponseMessage message = new ReponseMessage("Cette production existe déja !", false);
        return message;



    }*/
}}
