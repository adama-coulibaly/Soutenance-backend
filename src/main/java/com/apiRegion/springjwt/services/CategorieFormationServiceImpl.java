package com.apiRegion.springjwt.services;

import com.apiRegion.springjwt.Message.ReponseMessage;
import com.apiRegion.springjwt.models.CategorieFormation;
import com.apiRegion.springjwt.models.Formation;
import com.apiRegion.springjwt.models.User;
import com.apiRegion.springjwt.repository.CategorieFormationRepository;
import com.apiRegion.springjwt.repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CategorieFormationServiceImpl implements CategorieFormationService {


    @Autowired
    private CategorieFormationRepository categorieFormationRepository;

    @Override
    public ReponseMessage Ajouter(CategorieFormation categorieFormation) {
        if(categorieFormationRepository.findByTitreCategorie(categorieFormation.getTitreCategorie()) == null){

            categorieFormation.setEtat(true);
            categorieFormation.setNbreTuto(0L);

            categorieFormationRepository.save(categorieFormation);
            ReponseMessage message = new ReponseMessage("Catégorie créer avec succès",true);
            return message;
        }
        else{
            ReponseMessage message = new ReponseMessage("Cette Catégorie existe déjà !",false);
            return message;
        }
    }

    @Override
    public ReponseMessage Modifier(CategorieFormation categorieFormation, Long idcategorie) {
        Optional<CategorieFormation> formation1 = categorieFormationRepository.findById(idcategorie);
        if(!formation1.isPresent()){

            ReponseMessage message = new ReponseMessage("Cette catégorie n'est pas trouvée !", false);
            return message;
        }
        else {

            CategorieFormation formation2 = categorieFormationRepository.findById(idcategorie).get();
            formation2.setTitreCategorie(categorieFormation.getTitreCategorie());
            formation2.setDescription(categorieFormation.getDescription());
           formation2.setEtat(categorieFormation.isEtat());

            this.categorieFormationRepository.save(formation2);

            ReponseMessage message = new ReponseMessage("Catégorie modifiée avec succès !", true);
            return message;


        }
    }

    @Override
    public ReponseMessage ModifierImage(CategorieFormation categorieFormation, Long id) {
        if(categorieFormationRepository.findById(id) != null){
            CategorieFormation formation1 = categorieFormationRepository.findById(id).get();

            formation1.setPhotocategorie(categorieFormation.getPhotocategorie());
            categorieFormationRepository.save(formation1);
            ReponseMessage message = new ReponseMessage("Image modifiée avec succès !",true);
            return message;
        }
        else{

            ReponseMessage message = new ReponseMessage("Image non trouver !",true);
            return message;

        }
    }

    @Override
    public ReponseMessage Supprimer(Long idCategorie) {
        Optional<CategorieFormation> formation = categorieFormationRepository.findById(idCategorie);
        if(!formation.isPresent()){

            ReponseMessage message = new ReponseMessage("Cette catégorie n'est pas trouvée !", false);
            return message;
        }else{
            this.categorieFormationRepository.delete(formation.get());
            ReponseMessage message = new ReponseMessage("Catégorie supprimer avec succès!", true);
            return message;

        }
    }

    @Override
    public ReponseMessage SetEtat(CategorieFormation categorieFormation, Long idcategorie) {
        Optional<CategorieFormation> formation1 = categorieFormationRepository.findById(idcategorie);
        if(formation1.isPresent()){
            CategorieFormation formation2 = categorieFormationRepository.findById(idcategorie).get();
            formation2.setEtat(categorieFormation.isEtat());
            this.categorieFormationRepository.save(formation2);
            ReponseMessage message = new ReponseMessage("Etat modifiée avec succès !", true);
            return message;
        }
        else {
            ReponseMessage message = new ReponseMessage("Catégories non modifiés !", false);
            return message;


        }
    }

    @Override
    public List<CategorieFormation> Lister() {
        return categorieFormationRepository.findAll();
    }
}
