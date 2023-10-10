package com.apiRegion.springjwt.Notifications;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.stereotype.Service;

@Service
public class FireBaseService {

    private final FirebaseMessaging firebaseMessaging;

    public FireBaseService(FirebaseMessaging firebaseMessaging) {
        this.firebaseMessaging = firebaseMessaging;
    }

    public  String SendNotification(SendNotification sendNotification){
        Notification notification = Notification
                .builder()
                .setTitle(sendNotification.getTitre())
                .setBody(sendNotification.getContenu())
                .setImage(sendNotification.getImage())
                .build();
        Message message = Message
                .builder()
                .setToken(sendNotification.getToken())
                .setNotification(notification)
                .putAllData(sendNotification.getData())
                .build();

        try {
            firebaseMessaging.send(message);
            return "Message envoyer avec succès";
        }
        catch (FirebaseMessagingException e){
            e.printStackTrace();
            return "Impossible d'envoyer la notif";
        }
    }
}
