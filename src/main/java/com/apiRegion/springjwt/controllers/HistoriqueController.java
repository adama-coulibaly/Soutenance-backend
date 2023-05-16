package com.apiRegion.springjwt.controllers;

import com.apiRegion.springjwt.Message.ReponseMessage;
import com.apiRegion.springjwt.models.Ferme;
import com.apiRegion.springjwt.models.Historique;
import com.apiRegion.springjwt.repository.FermeRepository;
import com.apiRegion.springjwt.repository.HistoriqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/historique")
@CrossOrigin(origins = "*", maxAge = 3600)
public class HistoriqueController {

    @Autowired
    private HistoriqueRepository historiqueRepository;
    @Autowired
    private FermeRepository fermeRepository;

    @GetMapping("/lesHistoriqueParFerme/{ferme}")
    public List<Historique> mesHistos(@PathVariable("ferme")Ferme ferme){
        return historiqueRepository.findByFerme(ferme);
    }


    @GetMapping("/lister")
    public List<Historique> lesHistos(){
        return historiqueRepository.findAll();
    }

    @DeleteMapping(path = "/supprimer/{idhistorique}", name = "supprimer")
    //  @ResponseStatus(HttpStatus.NO_CONTENT)
    public ReponseMessage supprimer(@PathVariable Long idhistorique) {
       Historique historique = historiqueRepository.findById(idhistorique).get();


        this.historiqueRepository.delete(historique);
        ReponseMessage message = new ReponseMessage("Historique supprimé avec succès !", true);
        return message;
    }
}
