package com.apiRegion.springjwt.controllers;

import com.apiRegion.springjwt.Message.ReponseMessage;
import com.apiRegion.springjwt.models.Entretien;
import com.apiRegion.springjwt.models.Production;
import com.apiRegion.springjwt.repository.EntretienRepository;
import com.apiRegion.springjwt.repository.ProductionRepository;
import com.apiRegion.springjwt.services.EntretienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/entretien")
public class EntretienController {
    @Autowired
    private EntretienService entretienService;
    @Autowired
    private ProductionRepository productionRepository;
    @Autowired
    private EntretienRepository entretienRepository;

    @PostMapping("/ajouter/{idproduction}")
    public ReponseMessage ajouterEntretien(@RequestBody Entretien entretien, @PathVariable("idproduction") Production production){

                  entretien.setProduction(production);
          return   entretienService.Ajouter(entretien,production);

    }
}
