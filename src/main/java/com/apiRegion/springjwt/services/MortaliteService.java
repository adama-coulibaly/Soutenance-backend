package com.apiRegion.springjwt.services;

import com.apiRegion.springjwt.Message.ReponseMessage;
import com.apiRegion.springjwt.models.Mortalite;
import com.apiRegion.springjwt.models.Production;
import com.apiRegion.springjwt.models.Theme;
import com.apiRegion.springjwt.models.User;

import java.util.List;

public interface MortaliteService {

    ReponseMessage Ajouter(Mortalite mortalite, Production production);

    ReponseMessage Modifier(Mortalite mortalite, Long idmortalite);

    ReponseMessage Supprimer(Long idmortalite);

    List<Mortalite> Lister();
}
