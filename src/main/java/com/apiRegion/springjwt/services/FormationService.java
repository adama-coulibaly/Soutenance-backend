package com.apiRegion.springjwt.services;

import com.apiRegion.springjwt.Message.ReponseMessage;
import com.apiRegion.springjwt.models.Ferme;
import com.apiRegion.springjwt.models.Formation;
import com.apiRegion.springjwt.models.User;

import java.util.List;

public interface FormationService {

    ReponseMessage Ajouter(Formation formation, User user_id);

    ReponseMessage Modifier(Formation formation, Long idformation);

    ReponseMessage ModifierImage(Formation formation,Long id);


    ReponseMessage Supprimer(Long idformation);

    ReponseMessage SetEtat(Formation formation, Long idformation);

    List<Formation> Lister();

}
