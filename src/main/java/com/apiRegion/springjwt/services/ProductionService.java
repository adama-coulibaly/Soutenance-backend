package com.apiRegion.springjwt.services;

import com.apiRegion.springjwt.Message.ReponseMessage;
import com.apiRegion.springjwt.models.*;

import java.util.List;

public interface ProductionService {

    ReponseMessage Ajouter(Production production, Typeproduction typeproduction, Ferme idferme);

    ReponseMessage Modifier(Production production,  Long id );

    ReponseMessage Supprimer(Long idproduit, Long id);

    List<Theme> Lister();
}
