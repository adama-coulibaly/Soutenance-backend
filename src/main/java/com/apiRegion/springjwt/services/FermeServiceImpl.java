package com.apiRegion.springjwt.services;

import com.apiRegion.springjwt.Message.ReponseMessage;
import com.apiRegion.springjwt.models.Ferme;
import com.apiRegion.springjwt.models.User;
import com.apiRegion.springjwt.repository.FermeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FermeServiceImpl implements FermeService {

    @Autowired
    private FermeRepository fermeRepository;

/// ================================================== AJOUTER
    @Override
    public ReponseMessage Ajouter(Ferme ferme, User user_id) {

        if(fermeRepository.findByNomferme(ferme.getNomferme()) == null){
            ferme.setEtat(false);
            ferme.setUser(user_id);
            fermeRepository.save(ferme);
            ReponseMessage message = new ReponseMessage("Ferme créer avec succès",true);
            return message;
        }
        else{
            ReponseMessage message = new ReponseMessage("Ferme existe déjà !",false);
            return message;
        }

    }

    ///====================================================================MODIFIER

    @Override
    public ReponseMessage Modifier(Ferme ferme,Long idferme) {

        Optional<Ferme> ferme1 = fermeRepository.findById(idferme);
        if(!ferme1.isPresent()){

            ReponseMessage message = new ReponseMessage("Cette ferme n'est pas trouvée !", false);
            return message;
        }
        else {

            Ferme ferme2 = fermeRepository.findById(idferme).get();
            ferme2.setNomferme(ferme.getNomferme());
            ferme2.setActiviteferme(ferme.getActiviteferme());
            ferme2.setImageferme(ferme.getImageferme());
            ferme2.setEtat(ferme.isEtat());
            this.fermeRepository.save(ferme);

            ReponseMessage message = new ReponseMessage("Ferme modifiée avec succès !", true);
            return message;


        }

    }

    @Override
    public ReponseMessage Supprimer(Long idferme) {

        Optional<Ferme> ferme1 = fermeRepository.findById(idferme);
        if(!ferme1.isPresent()){

            ReponseMessage message = new ReponseMessage("Cette ferme n'est pas trouvée !", false);
            return message;
        }else{
            this.fermeRepository.delete(ferme1.get());
            ReponseMessage message = new ReponseMessage("ferme supprimer avec succès!", true);
            return message;

        }

    }

    @Override
    public List<Ferme> Lister() {
        return this.fermeRepository.findAll();
    }
}
