package com.apiRegion.springjwt.servicesImpl;

import com.apiRegion.springjwt.Message.ReponseMessage;
import com.apiRegion.springjwt.models.Mortalite;
import com.apiRegion.springjwt.models.Production;
import com.apiRegion.springjwt.repository.MortaliteRepository;
import com.apiRegion.springjwt.repository.ProductionRepository;
import com.apiRegion.springjwt.services.MortaliteService;
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
                return new ReponseMessage("Veuillez donner une date inferieur ou égal a la date du jour ", false);
            }
            else{
                mortaliteRepository.save(mortalite);
                return new ReponseMessage("Mortalité ajouté avec succès", true);
            }




    }

    @Override
    public ReponseMessage Modifier(Mortalite mortalite, Long idmortalite) {

        if (mortaliteRepository.findById(idmortalite).isPresent()){

            Mortalite mortalite1 = mortaliteRepository.findById(idmortalite).get();

            mortalite1.setDate(mortalite.getDate());
            mortalite1.setNombredeces(mortalite.getNombredeces());
            mortalite1.setCausedeces(mortalite.getCausedeces());
            mortalite1.setProduction(mortalite.getProduction());
            LocalDate today = LocalDate.now();
            if(mortalite.getDate().isAfter(today)){
                return new ReponseMessage("Veuillez donner une date inferieur ou égal a la date du jour ", false);
            }
            else{
                mortaliteRepository.save(mortalite1);
                return new ReponseMessage("Mortalité modifiée avec succès", true);
            }


        }
        else {
            return new ReponseMessage("Cette mortalité n'existe pas !", false);

        }


    }

    @Override
    public ReponseMessage Supprimer(Long idmortalite) {

        Optional<Mortalite> m = mortaliteRepository.findById(idmortalite);
        if (m.isPresent()){
            mortaliteRepository.delete(m.get());
            return new ReponseMessage("Mortalité supprimée avec succès", true);

        }
        else {
            return new ReponseMessage("Impossible de touver la mortalité", false);
        }
    }

    @Override
    public List<Mortalite> Lister() {
        return mortaliteRepository.findAll();
    }
}
