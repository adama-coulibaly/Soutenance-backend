package com.apiRegion.springjwt.services;

import com.apiRegion.springjwt.Message.ReponseMessage;
import com.apiRegion.springjwt.models.Mortalite;
import com.apiRegion.springjwt.models.Production;
import com.apiRegion.springjwt.repository.MortaliteRepository;
import com.apiRegion.springjwt.repository.ProductionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class MortaliteServiceImpl implements MortaliteService {

    @Autowired
    private MortaliteRepository mortaliteRepository;
    @Autowired
    private ProductionRepository productionRepository;


    @Override
    public ReponseMessage Ajouter(Mortalite mortalite, Production production) {


            mortalite.setProduction(production);
            LocalDate today = LocalDate.now();
            if(mortalite.getDate().isAfter(today)){
                ReponseMessage message = new ReponseMessage("Veuillez donner une date inferieur ou égal a la date du jour ", false);
                return message;
            }
            else{
                mortaliteRepository.save(mortalite);
                ReponseMessage message = new ReponseMessage("Mortalité ajouté avec succès", true);
                return message;
            }




    }

    @Override
    public ReponseMessage Modifier(Mortalite mortalite, Long idmortalite) {

        if (mortaliteRepository.findById(idmortalite) != null){

            Mortalite mortalite1 = mortaliteRepository.findById(idmortalite).get();

            mortalite1.setDate(mortalite.getDate());
            mortalite1.setNombredeces(mortalite.getNombredeces());
            mortalite1.setCausedeces(mortalite.getCausedeces());
            mortalite1.setProduction(mortalite.getProduction());
            LocalDate today = LocalDate.now();
            if(mortalite.getDate().isAfter(today)){
                ReponseMessage message = new ReponseMessage("Veuillez donner une date inferieur ou égal a la date du jour ", false);
                return message;
            }
            else{
                mortaliteRepository.save(mortalite1);
                ReponseMessage message = new ReponseMessage("Mortalité modifiée avec succès", true);
                return message;
            }


        }
        else {
            ReponseMessage message = new ReponseMessage("Cette mortalité n'existe pas !", false);
            return message;

        }


    }

    @Override
    public ReponseMessage Supprimer(Long idmortalite) {

        Optional<Mortalite> m = mortaliteRepository.findById(idmortalite);
        if (m.isPresent()){
            mortaliteRepository.delete(m.get());
            ReponseMessage message = new ReponseMessage("Mortalité supprimée avec succès", true);
            return message;

        }
        else {
            ReponseMessage message = new ReponseMessage("Impossible de touver la mortalité", false);
            return message;
        }
    }

    @Override
    public List<Mortalite> Lister() {
        return mortaliteRepository.findAll();
    }
}
