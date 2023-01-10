package com.apiRegion.springjwt.controllers;

import com.apiRegion.springjwt.Message.ReponseMessage;
import com.apiRegion.springjwt.models.*;
import com.apiRegion.springjwt.repository.MortaliteRepository;
import com.apiRegion.springjwt.services.MortaliteService;
import com.apiRegion.springjwt.services.ProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/mortalite")
public class MortaliteController {

    @Autowired
    private MortaliteService mortaliteService;
    @Autowired
    private MortaliteRepository mortaliteRepository;
    @Autowired
    private ProductionService productionService;

    @PostMapping("/ajouter/{idproduction}")
    public ReponseMessage ajouter(@RequestBody Mortalite mortalite, @PathVariable("idproduction") Production idproduction){

        return this.mortaliteService.Ajouter(mortalite, idproduction);
    }



    @PutMapping("/modifier/{idmortalite}")
    public ReponseMessage Modifier(@RequestBody Mortalite mortalite, @PathVariable("idmortalite") Long idmortalite) {
        mortalite.setProduction(mortalite.getProduction());
        return this.mortaliteService.Modifier(mortalite,idmortalite);
    }

    @DeleteMapping("/supprimer/{idmortalite}")
    public ReponseMessage Supprimer(@PathVariable("idmortalite") Long idmortalite) {
        return this.mortaliteService.Supprimer(idmortalite);
    }


    @GetMapping("/lister")
    public List<Mortalite> liste() {
        return  this.mortaliteService.Lister();
    }

    @GetMapping("/listerParProduction/{production}")
    public List<Mortalite> listerParProduction(@PathVariable("production") Production production) {
        return  this.mortaliteRepository.findByProduction(production);
    }
}
