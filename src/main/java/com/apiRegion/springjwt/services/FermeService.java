package com.apiRegion.springjwt.services;

import com.apiRegion.springjwt.Message.ReponseMessage;
import com.apiRegion.springjwt.models.Ferme;
import com.apiRegion.springjwt.models.Production;
import com.apiRegion.springjwt.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

;@Service
public interface FermeService {

    ReponseMessage Ajouter(Ferme ferme, User user_id);

    ReponseMessage Modifier(Ferme ferme, Long idferme);

    ReponseMessage Supprimer(Long idferme);

    ReponseMessage SetEtat(Ferme ferme, Long idferme);

    List<Ferme> Lister();
}
