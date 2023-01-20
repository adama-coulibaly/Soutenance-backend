package com.apiRegion.springjwt.services;

import com.apiRegion.springjwt.Message.ReponseMessage;
import com.apiRegion.springjwt.models.Ferme;
import com.apiRegion.springjwt.models.Formation;
import com.apiRegion.springjwt.models.User;
import com.apiRegion.springjwt.repository.FormationRepository;
import com.apiRegion.springjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FormationServiceImpl implements FormationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FormationRepository formationRepository;

    @Override
    public ReponseMessage Ajouter(Formation formation, User user_id) {

        if(formationRepository.findByTitreformation(formation.getTitreformation()) == null){

            formation.setEtat(true);
            //formation.getUsers().add(user_id);
            formation.setDatedeposte(LocalDate.now());
            formationRepository.save(formation);
            ReponseMessage message = new ReponseMessage("Formation créer avec succès",true);
            return message;
        }
        else{
            ReponseMessage message = new ReponseMessage("Formation existe déjà !",false);
            return message;
        }
    }

    @Override
    public ReponseMessage Modifier(Formation formation, Long idformation) {

        Optional<Formation> formation1 = formationRepository.findById(idformation);
        if(!formation1.isPresent()){

            ReponseMessage message = new ReponseMessage("Cette formation n'est pas trouvée !", false);
            return message;
        }
        else {

            Formation formation2 = formationRepository.findById(idformation).get();
            formation2.setTitreformation(formation.getTitreformation());
            formation2.setDureformation(formation.getDureformation());
            formation2.setPhotoformation(formation.getPhotoformation());

            this.formationRepository.save(formation2);

            ReponseMessage message = new ReponseMessage("Formation modifiée avec succès !", true);
            return message;


        }
    }

    @Override
    public ReponseMessage Supprimer(Long idformation) {
        Optional<Formation> formation = formationRepository.findById(idformation);
        if(!formation.isPresent()){

            ReponseMessage message = new ReponseMessage("Cette formation n'est pas trouvée !", false);
            return message;
        }else{
            this.formationRepository.delete(formation.get());
            ReponseMessage message = new ReponseMessage("Formation supprimer avec succès!", true);
            return message;

        }
    }

    @Override
    public ReponseMessage SetEtat(Formation formation, Long idformation) {

        Optional<Formation> formation1 = formationRepository.findById(idformation);
        if(formation1.isPresent()){
            Formation formation2 = formationRepository.findById(idformation).get();
            formation2.setEtat(formation.isEtat());
            this.formationRepository.save(formation2);
            ReponseMessage message = new ReponseMessage("Etat modifiée avec succès !", true);
            return message;
        }
        else {
            ReponseMessage message = new ReponseMessage("Formation non modifiés !", false);
            return message;


        }
    }

    @Override
    public List<Formation> Lister() {
        return formationRepository.findAll();
    }
}
