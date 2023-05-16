package com.apiRegion.springjwt.services;

import com.apiRegion.springjwt.Message.ReponseMessage;
import com.apiRegion.springjwt.models.Commentaire;
import com.apiRegion.springjwt.models.Theme;
import com.apiRegion.springjwt.models.User;

import java.util.List;

public interface CommentaireService {


    ReponseMessage Ajouter(Commentaire commentaire,Theme idtheme, User user_id);

    ReponseMessage Modifier(Commentaire commentaire, Long idtheme, Long id );

    ReponseMessage Supprimer(Long idcommentaire , Long id);

    List<Commentaire> Lister();
}
