package com.apiRegion.springjwt.services;

import com.apiRegion.springjwt.Message.ReponseMessage;
import com.apiRegion.springjwt.models.Entretien;
import com.apiRegion.springjwt.models.Production;
import com.apiRegion.springjwt.repository.EntretienRepository;
import org.joda.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EntretienServiceImpl implements EntretienService {
    @Autowired
    private EntretienRepository entretienRepository;

    @Override
    public ReponseMessage Ajouter(Entretien entretien, Production production) {
        entretien.setProduction(production);
        LocalDate today = LocalDate.now();
        if(entretien.getDateentretien().isBefore(today)){
            ReponseMessage message = new ReponseMessage("Veuillez donner une date superieir ou égal a la date du jour ", false);
            return message;
        } else if (entretienRepository.existsByDateentretienAndProduction(entretien.getDateentretien(),production)) {
            ReponseMessage message = new ReponseMessage("Un entretien coincide cet entretien !", false);
            return message;
        } else{
            entretienRepository.save(entretien);
            ReponseMessage message = new ReponseMessage("Entretien ajouté avec succès", true);
            return message;
        }
    }

    @Override
    public ReponseMessage Modifier(Entretien entretien, Long identretien) {
        return null;
    }

    @Override
    public ReponseMessage Supprimer(Long identretien) {
        return null;
    }

    @Override
    public List<Entretien> Lister() {
        return null;
    }
}
