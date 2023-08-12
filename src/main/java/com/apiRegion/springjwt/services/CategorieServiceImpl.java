package com.apiRegion.springjwt.services;

import com.apiRegion.springjwt.Message.ReponseMessage;
import com.apiRegion.springjwt.models.CategorieProd;
import com.apiRegion.springjwt.models.Typeproduction;
import com.apiRegion.springjwt.repository.CategorieRepository;
import com.apiRegion.springjwt.repository.TypeProdRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategorieServiceImpl implements CategorieService {


    private CategorieRepository categorieRepository;


    @Override
    public ReponseMessage Ajouter(CategorieProd categorieProd) {
        if (categorieRepository.findByNomcategories(categorieProd.getNomcategories()) == null){
            categorieRepository.save(categorieProd);
            ReponseMessage message = new ReponseMessage("TCatégorie ajouté avec succes !", true);
            return message;
        }
        else{
            ReponseMessage message = new ReponseMessage("Ce type de catégorie existe déjà !", false);
            return message;

        }

    }

    @Override
    public ReponseMessage Modifier(CategorieProd categorieProd, Long idtype) {
        Optional<CategorieProd> typeproduction1 = categorieRepository.findById(idtype);
        if(!typeproduction1.isPresent()){

            ReponseMessage message = new ReponseMessage("Cette catégorie n'est pas trouvée !", false);
            return message;
        }
        else {

            CategorieProd typeproduction2 = categorieRepository.findById(idtype).get();
            typeproduction2.setNomcategories(typeproduction2.getNomcategories());

            this.categorieRepository.save(typeproduction2);

            ReponseMessage message = new ReponseMessage("Catégorie modifiée avec succès !", true);
            return message;


        }
    }

    @Override
    public ReponseMessage Supprimer(Long idtype) {
        Optional<CategorieProd> type = this.categorieRepository.findById(idtype);
        if (!type.isPresent())
        {
            ReponseMessage message = new ReponseMessage("Catégorie non trouvée !", false);
            return message;
        }
        else {
            this.categorieRepository.delete(type.get());
            ReponseMessage message = new ReponseMessage("Type supprimé avec succès !", true);
            return message;
        }
    }

    @Override
    public List<CategorieProd> Lister() {
        return this.categorieRepository.findAll();
    }
}
