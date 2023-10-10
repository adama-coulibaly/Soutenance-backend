package com.apiRegion.springjwt.servicesImpl;

import com.apiRegion.springjwt.models.NotificationPush;
import com.apiRegion.springjwt.models.User;
import com.apiRegion.springjwt.repository.UserRepository;
import com.google.firebase.FirebaseException;
import com.google.firebase.messaging.BatchResponse;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.MulticastMessage;
import com.google.firebase.messaging.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service pour l'envoi de notifications Firebase Cloud Messaging (FCM).
 */
@Service
public class FirebaseMessagingService {
    @Autowired
    private FirebaseMessaging firebaseMessaging;
    @Autowired
    private UserRepository utilisateurRepo;

    /**
     * Envoie une notification à un jeton de périphérique spécifique.
     *
     * @param notificationMessaging Les détails du message de
     *                              notification.
     * @return Une chaîne indiquant le succès ou l'échec de l'envoi de
     * la notification.
     */
    public String sendNotificationByToken(NotificationPush
                                                  notificationMessaging) {
        Notification notification = Notification
                .builder()
                .setTitle(notificationMessaging.getTitle())
                .setBody(notificationMessaging.getBody())
                .setImage(notificationMessaging.getImage())
                .build();
        Message message = Message
                .builder()
                .setToken(notificationMessaging.getToken())
                .setNotification(notification)
                .putAllData(notificationMessaging.getData())
                .build();
        try {
            firebaseMessaging.send(message);
            return "Notification envoyée avec succès !";
        } catch (FirebaseException e) {
// TODO: gérer l'exception
            e.printStackTrace();
            return "Erreur lors de l'envoi de la notification : " +
                    e.getMessage();
        }
    }
    /**
     * Envoie une notification multicast à plusieurs appareils.
     *
     * @param notificationMessaging Les détails du message de
    notification.
     * @return Une chaîne indiquant le succès ou l'échec de l'envoi de
    la notification.
     */
  /*  public String sendMultiCastNotification(NotificationPush notificationMessaging){
// Récupérer les jetons de périphérique depuis la base de données

        List<User> utilisateurs = utilisateurRepo.findAll();
        List<String> tokens = new ArrayList();
        for (User utilisateur : utilisateurs) {
            tokens.add(utilisateur.getDeviceToken());
        }
// Construire la notification
        Notification notification = Notification.builder()
                .setTitle(notificationMessaging.getTitle())
                .setBody(notificationMessaging.getBody())
                .setImage(notificationMessaging.getImage())
                .build();
// Construire le message multicast
        MulticastMessage message = MulticastMessage.builder()
                .setNotification(notification)
                .addAllTokens(tokens)
                .putAllData(notificationMessaging.getData())
                .build();
        try {
            BatchResponse response =firebaseMessaging.sendMulticast(message);
// Traiter la réponse si nécessaire
            return "Notification envoyée avec succès à tous les utilisateurs !"+ response;
        } catch (FirebaseMessagingException e) {
// Gérer les erreurs d'envoi de la notification
            e.printStackTrace();
            return "Erreur lors de l'envoi de la notification : " +
                    e.getMessage();
        }
    }

   */
}