package com.apiRegion.springjwt.services;

import com.apiRegion.springjwt.Message.ReponseMessage;
import com.apiRegion.springjwt.models.User;
import com.apiRegion.springjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserModifierImpl implements UserModifier {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;
    @Override
    public ReponseMessage Modifier(User user, Long id) {

        if(userRepository.findById(id) == null){
            ReponseMessage message = new ReponseMessage("Cet utilisateur n'existe pas !",false);
            return message;
        }
        else{
            User user1 = userRepository.findById(id).get();
            user1.setNom(user.getNom());
            user1.setPrenom(user.getPrenom());
            user1.setAdresse(user.getAdresse());
            user1.setEmail(user.getEmail());
            user1.setPassword(passwordEncoder.encode(user.getPassword()));
            user1.setUsername(user.getUsername());
            //user1.setAvatar(user.getAvatar());

            this.userRepository.save(user1);
            ReponseMessage message = new ReponseMessage("Modification reussie avec succès !",true);
            return message;

        }
    }

    @Override
    public ReponseMessage ModifierAvatar(User user, Long id) {

        if(userRepository.findById(id) != null){
            User user1 = userRepository.findById(id).get();

            user1.setAvatar(user.getAvatar());
            userRepository.save(user1);
            ReponseMessage message = new ReponseMessage("Image modifiée avec succès !",true);
            return message;
        }
        else{

            ReponseMessage message = new ReponseMessage("Image non trouver !",true);
            return message;

        }
    }
}
