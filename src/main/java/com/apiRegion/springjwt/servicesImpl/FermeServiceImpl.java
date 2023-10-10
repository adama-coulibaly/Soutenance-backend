package com.apiRegion.springjwt.servicesImpl;

import com.apiRegion.springjwt.Message.ReponseMessage;
import com.apiRegion.springjwt.models.Ferme;
import com.apiRegion.springjwt.models.Production;
import com.apiRegion.springjwt.models.User;
import com.apiRegion.springjwt.repository.FermeRepository;
import com.apiRegion.springjwt.services.FermeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FermeServiceImpl implements FermeService {


    private final FermeRepository fermeRepository;

/// ================================================== AJOUTER
    @Override
    public ReponseMessage Ajouter(Ferme ferme, User user_id) {

        if(fermeRepository.findByNomferme(ferme.getNomferme()) == null){
            ferme.setEtat(true);
            ferme.setUser(user_id);
            fermeRepository.save(ferme);
            return new ReponseMessage("Ferme créer avec succès",true);
        }
        else{
            return new ReponseMessage("Ce non existe déjà !",false);
        }

    }

    ///====================================================================MODIFIER

    @Override
    public ReponseMessage Modifier(Ferme ferme,Long idferme) {

        Optional<Ferme> ferme1 = fermeRepository.findById(idferme);
        if(!ferme1.isPresent()){

            return new ReponseMessage("Cette ferme n'est pas trouvée !", false);
        }
        else {

            Ferme ferme2 = fermeRepository.findById(idferme).get();
            ferme2.setNomferme(ferme.getNomferme());
            ferme2.setActiviteferme(ferme.getActiviteferme());
            ferme2.setImageferme(ferme.getImageferme());
            ferme2.setAdresseferme(ferme.getAdresseferme());
            ferme2.setTaille(ferme.getTaille());
            ferme2.setEtat(ferme.isEtat());
            this.fermeRepository.save(ferme2);

            return new ReponseMessage("Ferme modifiée avec succès !", true);


        }

    }

    @Override
    public ReponseMessage Supprimer(Long idferme) {

        Optional<Ferme> ferme1 = fermeRepository.findById(idferme);
        if(!ferme1.isPresent()){

            return new ReponseMessage("Cette ferme n'est pas trouvée !", false);
        }else{
            this.fermeRepository.delete(ferme1.get());
            return new ReponseMessage("ferme supprimer avec succès!", true);

        }

    }

    @Override
    public ReponseMessage SetEtat(Ferme ferme, Long idferme) {

            Optional<Ferme> ferme1 = fermeRepository.findById(idferme);
            if(ferme1.isPresent()){
                Ferme ferme2 = fermeRepository.findById(idferme).get();
                ferme2.setEtat(ferme.isEtat());
                this.fermeRepository.save(ferme2);
                return new ReponseMessage("Etat modifiée avec succès !", true);
            }
            else {
                return new ReponseMessage("Ferme non modifiés !e", false);
        }
    }

    @Override
    public List<Ferme> Lister() {
        return this.fermeRepository.findAll();
    }
}
