package com.apiRegion.springjwt.services;

import com.apiRegion.springjwt.controllers.ProductionController;
import com.apiRegion.springjwt.models.Entretien;
import com.apiRegion.springjwt.models.NotificationSender;
import com.apiRegion.springjwt.models.Production;
import com.apiRegion.springjwt.models.Status;
import com.apiRegion.springjwt.repository.EntretienRepository;
import com.apiRegion.springjwt.repository.NotificationSenderRepository;
import com.apiRegion.springjwt.repository.ProductionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthomatisationImpl implements Authomatisation {

    @Autowired
    private ProductionRepository productionRepository;
    @Autowired
    private ProductionService productionService;

    @Autowired
    private EntretienService entretienService;
    @Autowired
    private EntretienRepository entretienRepository;
    @Autowired
    private NotificationSenderRepository notificationSenderRepository;

    // CETTE FONCTION NOUS PERMET DE MODIFIER LE STATUS DES PRODUCTIONS EN FONCTION DE LA DATE DU JOURS
    @Scheduled(fixedDelay = 86400000)
    @Override
    public void verification() {
        List<Production> mesProductions = productionRepository.findAll();
        System.out.println("La f taille "+mesProductions.size());


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

    @Scheduled(fixedDelay = 60000)
    @Override
    public void entretienNotification() {
        List<Entretien> mesEntretiens = entretienRepository.findByDateentretien(LocalDate.now());
           for(Entretien entretien:mesEntretiens){
               if(LocalDate.now().isEqual(entretien.getDateentretien())){

                   int minutesA = entretien.getHeuresentretien().getHour() * 60 + (entretien.getHeuresentretien().getMinute() - 15);
                   int minetesB = LocalTime.now().getHour() * 60 + LocalTime.now().getMinute();

                   if(minutesA == minetesB){
                       NotificationSender notif = new NotificationSender();
                       notif.setDatedenvoi(LocalDate.now());
                       notif.setTitrenotification("Entretien du "+entretien.getDateentretien());
                       //notif.setMessagenotification("Votre heure d'entretien : "+entretien.getTypeentretien()+" est dans 15 minutes. Soyez à l'heure !");
                       notif.setMessagenotification("Bonjour "+entretien.getProduction().getFerme().getUser().getNom()+" "+entretien.getProduction().getFerme().getUser().getPrenom()+" votre entretien: "+entretien.getTypeentretien()+"prévu aujourd'hui à "+entretien.getHeuresentretien()+" est dans 15 minutes. Soyez à l'heure !");
                       notif.setUser(entretien.getProduction().getFerme().getUser());
                       notif.setLire(false);
                       notificationSenderRepository.save(notif);
                   }

               }

           }
    }

}
