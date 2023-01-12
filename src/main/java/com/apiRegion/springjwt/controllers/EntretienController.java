package com.apiRegion.springjwt.controllers;

import com.apiRegion.springjwt.Message.ReponseMessage;
import com.apiRegion.springjwt.models.Commentaire;
import com.apiRegion.springjwt.models.Entretien;
import com.apiRegion.springjwt.models.Production;
import com.apiRegion.springjwt.models.Theme;
import com.apiRegion.springjwt.repository.EntretienRepository;
import com.apiRegion.springjwt.repository.ProductionRepository;
import com.apiRegion.springjwt.services.EntretienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ReponseMessage ajouterEntretien(@RequestBody Entretien entretien, @PathVariable("idproduction") Production production) {

        entretien.setProduction(production);
        return entretienService.Ajouter(entretien, production);

    }

    @PutMapping("/modifier/{identretien}")
    public ReponseMessage modifier(@RequestBody Entretien entretien, @PathVariable("identretien") Long identretien) {
        return entretienService.Modifier(entretien, identretien);
    }


    @GetMapping("/liste")
    public List<Entretien> toulesListe(){
        return this.entretienService.Lister();
    }

    @GetMapping("/listeParProduction/{idproduction}")
    public List<Entretien> entretienslist(Production production){
        return this.entretienRepository.findByProduction(production);
    }

    @DeleteMapping(path = "/supprimer/{identretien}", name = "supprimer")
    //  @ResponseStatus(HttpStatus.NO_CONTENT)
    public ReponseMessage supprimer(@PathVariable("identretien") Long identretien) {
        return this.entretienService.Supprimer(identretien);
    }

}
