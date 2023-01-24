package com.apiRegion.springjwt.services;

import com.apiRegion.springjwt.Message.ReponseMessage;
import com.apiRegion.springjwt.models.CategorieProd;
import com.apiRegion.springjwt.models.Typeproduction;

import java.util.List;

public interface CategorieService {

    ReponseMessage Ajouter(CategorieProd categorieProd);

    ReponseMessage Modifier(CategorieProd categorieProd, Long idcategorie );

    ReponseMessage Supprimer(Long idcategorie);

    List<CategorieProd> Lister();
}
