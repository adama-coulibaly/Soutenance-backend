package com.apiRegion.springjwt.servicesImpl;

import com.apiRegion.springjwt.Message.ReponseMessage;
import com.apiRegion.springjwt.models.Entretien;
import com.apiRegion.springjwt.models.Production;
import com.apiRegion.springjwt.models.Theme;
import com.apiRegion.springjwt.models.User;
import com.apiRegion.springjwt.repository.EntretienRepository;

import com.apiRegion.springjwt.services.EntretienService;
import org.joda.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EntretienServiceImpl implements EntretienService {
    @Autowired
    private EntretienRepository entretienRepository;

    @Override
    public ReponseMessage Ajouter(Entretien entretien, Production production) {
        entretien.setProduction(production);
        LocalDate today = LocalDate.now();

        System.out.println("Heure 1 "+LocalTime.now());
        System.out.println("Heure 2 "+entretien.getHeuresentretien());


        if(entretien.getDateentretien().isBefore(today)){
            ReponseMessage message = new ReponseMessage("Veuillez donner une date supperieur ou égal a la date du jour ", false);
            return message;
        } else if (entretienRepository.existsByDateentretienAndProduction(entretien.getDateentretien(),production)) {
            ReponseMessage message = new ReponseMessage("Un entretien coincide cet entretien !", false);
            return message;
        }
  /*  else if(entretien.getHeuresentretien().isBefore(LocalTime.now())){
            ReponseMessage message = new ReponseMessage("Veuillez donner une heure correct !", false);
            return message;
        }*/
        else{
            entretienRepository.save(entretien);
            ReponseMessage message = new ReponseMessage("Entretien ajouté avec succès", true);
            return message;
        }
    }

    @Override
    public ReponseMessage Modifier(Entretien entretien, Long identretien) {
        Optional<Entretien> p = entretienRepository.findById(identretien);
        LocalDate today = LocalDate.now();
        if(entretien.getDateentretien().isBefore(today)){
            return new ReponseMessage("Veuillez donner une date superieir ou égal a la date du jour ", false);
        } else if (Boolean.TRUE.equals(entretienRepository.existsByDateentretienAndProduction(entretien.getDateentretien(),p.get().getProduction()))) {
            return new ReponseMessage("Un entretien coincide cet entretien !", false);
        } else{
            Entretien entretien1 = entretienRepository.findById(identretien).get();
            entretien1.setDateentretien(entretien.getDateentretien());
            entretien1.setHeuresentretien(entretien.getHeuresentretien());
            entretien1.setTypeentretien(entretien.getTypeentretien());

            entretienRepository.save(entretien1);
            return new ReponseMessage("Entretien modifié avec succès", true);
        }
    }

    @Override
    public ReponseMessage Supprimer(Long identretien) {
        Optional<Entretien> en = entretienRepository.findById(identretien);
        if(!en.isPresent()){
            return new ReponseMessage("Impossible de supprimer !", false);
        }
        else {
           // this.entretienRepository.delete(en.get());
            this.entretienRepository.delete(en.get());
            return new ReponseMessage("Entretien supprimé avec succès !", true);
        }


    }


    @Override
    public ReponseMessage SupprimerPlus(List<Entretien> entretiens) {
        //<Entretien> en = entretienRepository.findById(entretiens.get(0));

      /*  if(!en.isPresent()){
            ReponseMessage message = new ReponseMessage("Impossible de supprimer !", false);
            return message;
        }
        else {*/
            // this.entretienRepository.delete(en.get());
            this.entretienRepository.deleteAll(entretiens);
        return new ReponseMessage("Entretien supprimé avec succès !", true);
       // }


    }

    @Override
    public List<Entretien> Lister() {
        return entretienRepository.findAll();
    }
}
