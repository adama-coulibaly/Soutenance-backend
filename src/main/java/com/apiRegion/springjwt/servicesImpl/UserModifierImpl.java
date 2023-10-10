package com.apiRegion.springjwt.servicesImpl;

import com.apiRegion.springjwt.Message.ReponseMessage;
import com.apiRegion.springjwt.models.Ferme;
import com.apiRegion.springjwt.models.NotificationSender;
import com.apiRegion.springjwt.models.User;
import com.apiRegion.springjwt.repository.NotificationSenderRepository;
import com.apiRegion.springjwt.repository.UserRepository;
import com.apiRegion.springjwt.security.EmailConstructor;
import com.apiRegion.springjwt.services.UserModifier;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserModifierImpl implements UserModifier {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private EmailConstructor emailConstructor;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private NotificationSenderRepository notificationSenderRepository;

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



    @Override
    public ReponseMessage SetLire(NotificationSender notificationSender, Long idnoyif) {
        Optional<NotificationSender> notificationSender1 = notificationSenderRepository.findById(idnoyif);
        if(notificationSender1.isPresent()){
            NotificationSender notificationSender2 = notificationSenderRepository.findById(idnoyif).get();
            notificationSender2.setLire(notificationSender.isLire());
            this.notificationSenderRepository.save(notificationSender2);
            ReponseMessage message = new ReponseMessage("Notification lue !", true);
            return message;
        }
        else {
            ReponseMessage message = new ReponseMessage("Non lue", false);
            return message;


        }    }

    @Override
    public void resetPassword(User user) {
        String password = RandomStringUtils.randomAlphanumeric(10);
        String encryptedPassword = bCryptPasswordEncoder.encode(password);
        user.setPassword(encryptedPassword);
        userRepository.save(user);
        mailSender.send(emailConstructor.constructResetPasswordEmail(user, password));
    }

    @Override
    public void updateUserPassword(User user, String newPassword) {
        String encryptedPassword = bCryptPasswordEncoder.encode(newPassword);
        user.setPassword(encryptedPassword);
        userRepository.save(user);
        mailSender.send(emailConstructor.constructResetPasswordEmail(user, newPassword));

    }

    @Override
    public ReponseMessage SetEtat(User user, Long id) {
        Optional<User> user1 = userRepository.findById(id);
        if(user1.isPresent()){
            User user2 = userRepository.findById(id).get();
            user2.setEtat(user.isEtat());
            this.userRepository.save(user2);
            ReponseMessage message = new ReponseMessage("Etat modifiée avec succès !", true);
            return message;
        }
        else {
            ReponseMessage message = new ReponseMessage("User non modifiés !e", false);
            return message;


        }
    }


}
