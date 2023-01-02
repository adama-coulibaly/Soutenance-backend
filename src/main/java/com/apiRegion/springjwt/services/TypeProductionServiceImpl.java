package com.apiRegion.springjwt.services;

import com.apiRegion.springjwt.Message.ReponseMessage;
import com.apiRegion.springjwt.models.Ferme;
import com.apiRegion.springjwt.models.Typeproduction;
import com.apiRegion.springjwt.repository.TypeProdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class TypeProductionServiceImpl implements TypeProductionService {

    @Autowired
    private TypeProdRepository typeProdRepository;


    @Override
    public ReponseMessage Ajouter(Typeproduction typeproduction) {
        if (typeProdRepository.findByNomtype(typeproduction.getNomtype()) == null){
            typeProdRepository.save(typeproduction);
            ReponseMessage message = new ReponseMessage("Type de production ajouté avec succes !", true);
            return message;
        }
        else{
            ReponseMessage message = new ReponseMessage("Ce type de peoduction existe déjà !", false);
            return message;

        }

    }

    @Override
    public ReponseMessage Modifier(Typeproduction typeproduction, Long idtype) {
        Optional<Typeproduction> typeproduction1 = typeProdRepository.findById(idtype);
        if(!typeproduction1.isPresent()){

            ReponseMessage message = new ReponseMessage("Ce type n'est pas trouvée !", false);
            return message;
        }
        else {

            Typeproduction typeproduction2 = typeProdRepository.findById(idtype).get();
            typeproduction2.setNomtype(typeproduction.getNomtype());

            this.typeProdRepository.save(typeproduction2);

            ReponseMessage message = new ReponseMessage("Type modifiée avec succès !", true);
            return message;


        }
    }

    @Override
    public ReponseMessage Supprimer(Long idtype) {
        return null;
    }

    @Override
    public List<Typeproduction> Lister() {
        return this.typeProdRepository.findAll();
    }
}
