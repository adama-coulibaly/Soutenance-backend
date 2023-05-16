package com.apiRegion.springjwt.controllers;

import com.apiRegion.springjwt.Message.ReponseMessage;
import com.apiRegion.springjwt.models.*;
import com.apiRegion.springjwt.repository.PanierRepository;
import com.apiRegion.springjwt.services.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/commande")
@CrossOrigin(origins = "*", maxAge = 3600)

public class CommandeController {

    @Autowired
    private CommandeService commandeService;
    @Autowired
    private PanierRepository panierRepository;

    @PostMapping("/ajouter/{id}")
    public ReponseMessage ajouter(Commande commande, @PathVariable("id") User user){
          List<Panier> panier = panierRepository.findByUser(user);
        return commandeService.ajouter(commande,user);
    }
}
