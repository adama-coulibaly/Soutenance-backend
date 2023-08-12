package com.apiRegion.springjwt.services;

import com.apiRegion.springjwt.Message.ReponseMessage;
import com.apiRegion.springjwt.models.CategorieFormation;
import com.apiRegion.springjwt.models.Formation;
import com.apiRegion.springjwt.models.User;

import java.util.List;

public interface CategorieFormationService {

    ReponseMessage Ajouter(CategorieFormation categorieFormation);

    ReponseMessage Modifier(CategorieFormation categorieFormation, Long idcategorie);

    ReponseMessage ModifierImage(CategorieFormation categorieFormation,Long id);


    ReponseMessage Supprimer(Long idformation);

    ReponseMessage SetEtat(CategorieFormation categorieFormation, Long idcategorie);

    List<CategorieFormation> Lister();
}
