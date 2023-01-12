package com.apiRegion.springjwt.services;


import com.apiRegion.springjwt.Message.ReponseMessage;
import com.apiRegion.springjwt.models.Entretien;
import com.apiRegion.springjwt.models.Mortalite;
import com.apiRegion.springjwt.models.Production;

import java.util.List;

public interface EntretienService {
    ReponseMessage Ajouter(Entretien entretien, Production production);

    ReponseMessage Modifier(Entretien entretien, Long identretien);

    ReponseMessage Supprimer(Long identretien);

    List<Entretien> Lister();

}
