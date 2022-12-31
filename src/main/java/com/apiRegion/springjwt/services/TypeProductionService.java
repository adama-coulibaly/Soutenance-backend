package com.apiRegion.springjwt.services;

import com.apiRegion.springjwt.Message.ReponseMessage;
import com.apiRegion.springjwt.models.Ferme;
import com.apiRegion.springjwt.models.Typeproduction;
import com.apiRegion.springjwt.models.User;

import java.util.List;

public interface TypeProductionService {
    ReponseMessage Ajouter(Typeproduction typeproduction);

    ReponseMessage Modifier(Typeproduction typeproduction, Long idtype );

    ReponseMessage Supprimer(Long idtype);

    List<Typeproduction> Lister();
}
