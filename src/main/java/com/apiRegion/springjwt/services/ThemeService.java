package com.apiRegion.springjwt.services;

import com.apiRegion.springjwt.Message.ReponseMessage;
import com.apiRegion.springjwt.models.Theme;
import com.apiRegion.springjwt.models.Typeproduction;
import com.apiRegion.springjwt.models.User;

import java.util.List;

public interface ThemeService {

    ReponseMessage Ajouter(Theme theme, User user_id);

    ReponseMessage Modifier(Theme theme, Long idtheme, Long id );

    ReponseMessage Supprimer(Long idtype, Long id);

    List<Theme> Lister();
}
