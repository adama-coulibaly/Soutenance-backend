package com.apiRegion.springjwt.controllers;

import com.apiRegion.springjwt.Message.ReponseMessage;
import com.apiRegion.springjwt.models.CategorieProd;
import com.apiRegion.springjwt.models.Typeproduction;
import com.apiRegion.springjwt.repository.CategorieRepository;
import com.apiRegion.springjwt.repository.TypeProdRepository;
import com.apiRegion.springjwt.services.CategorieService;
import com.apiRegion.springjwt.services.TypeProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/categorie")
@CrossOrigin(origins = "*", maxAge = 3600)
public class Categorie {


    @Autowired
    private CategorieService categorieService;

    @Autowired
    private CategorieRepository categorieRepository;
    //============================================== AJOUT DES TYPES DE PRODUCTIONS
    @PostMapping("/ajouter")
    public ReponseMessage ajouter(@RequestBody CategorieProd categorieProd){
        return this.categorieService.Ajouter(categorieProd);
    }

    //============================================== ON LISTE LES TYPES DE PRODUCTIONS

    @GetMapping("/liste")
    public List<CategorieProd> lesProductions(){
        return this.categorieService.Lister();
    }

    //============================================== ON SUPPRIME UN TYPE DE PRODUCTION

    @DeleteMapping("supprimer/{idtype}")
    public ReponseMessage changeEtat(@PathVariable("idtype") Long idtype)
    {

        Optional<CategorieProd> categorieProd = this.categorieRepository.findById(idtype);
        if (!categorieProd.isPresent())
        {
            ReponseMessage message = new ReponseMessage("Type de production non trouvée !", false);
            return message;
        }
        else {
            this.categorieRepository.delete(categorieProd.get());
            ReponseMessage message = new ReponseMessage("Type de production supprimé avec succès !", true);
            return message;
        }

    }
    //============================================== ON MODIFIE UN TYPE DE PRODUCTION

    @PutMapping("/modifier/{idtype}")
    public ReponseMessage modifier(@RequestBody CategorieProd categorieProd, @PathVariable("idtype") Long idtype){

        if(categorieRepository.findById(idtype) != null){

            this.categorieService.Modifier(categorieProd,idtype);
            ReponseMessage message = new ReponseMessage("Type modifier avec succès !", true);
            return message;
        }
        else{
            ReponseMessage message = new ReponseMessage("Type de production non trouvé !", false);
            return message;
        }
    }

}


