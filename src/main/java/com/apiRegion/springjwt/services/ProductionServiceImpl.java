package com.apiRegion.springjwt.services;

import com.apiRegion.springjwt.Message.ReponseMessage;
import com.apiRegion.springjwt.models.Ferme;
import com.apiRegion.springjwt.models.Production;
import com.apiRegion.springjwt.models.Theme;
import com.apiRegion.springjwt.models.Typeproduction;
import com.apiRegion.springjwt.repository.ProductionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductionServiceImpl implements ProductionService {

    @Autowired
    private ProductionRepository productionRepository;
    @Override
    public ReponseMessage Ajouter(Production production, Typeproduction typeproduction, Ferme ferme) {
        production.getFerme().add(ferme);
       // production.getTypeproduction().add(typeproduction);
        production.setIdproduction(typeproduction.getIdtype());


        productionRepository.save(production);
        ReponseMessage message = new ReponseMessage("Production ajoutée avec succès !", true);
        return message;
    }

    @Override
    public ReponseMessage Modifier(Production production, Long id) {
        return null;
    }

    @Override
    public ReponseMessage Supprimer(Long idproduit, Long id) {
        return null;
    }

    @Override
    public List<Theme> Lister() {
        return null;
    }
}
