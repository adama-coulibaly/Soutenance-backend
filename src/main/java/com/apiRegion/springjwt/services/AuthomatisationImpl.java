package com.apiRegion.springjwt.services;

import com.apiRegion.springjwt.controllers.ProductionController;
import com.apiRegion.springjwt.models.Production;
import com.apiRegion.springjwt.models.Status;
import com.apiRegion.springjwt.repository.ProductionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthomatisationImpl implements Authomatisation {

    @Autowired
    private ProductionRepository productionRepository;
    @Autowired
    private ProductionService productionService;

    // CETTE FONCTION NOUS PERMET DE MODIFIER LE STATUS DES PRODUCTIONS EN FONCTION DE LA DATE DU JOURS
    @Scheduled(fixedDelay = 86400000)
    @Override
    public void verification() {
        List<Production> mesProductions = productionRepository.findAll();
        System.out.println("La taille "+mesProductions.size());


       for (Production p:mesProductions){
            if(LocalDate.now().isEqual(p.getDateentrer())){
                p.setStatus(Status.ENCOURS);
               productionService.setStatus(p,p.getIdproduction());
               System.out.println(" 2-Ici pour les ENCOURS");
            }
            else if(LocalDate.now().isAfter(p.getDatesortie())){
                p.setStatus(Status.TERMINER);
                System.out.println("1- Ici pour les Terminer");
                productionService.setStatus(p,p.getIdproduction());
            }
            else {
                return;
            }

        }



    }

}
