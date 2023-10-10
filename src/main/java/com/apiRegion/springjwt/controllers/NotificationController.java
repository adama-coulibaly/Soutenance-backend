package com.apiRegion.springjwt.controllers;

import com.apiRegion.springjwt.models.NotificationPush;
import com.apiRegion.springjwt.servicesImpl.FirebaseMessagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/mortalite")
@CrossOrigin(origins = "*", maxAge = 3600)
public class NotificationController {
@Autowired
private FirebaseMessagingService firebaseMessagingService;
/**
* Envoie une notification à un appareil spécifique.
*
* @param notificationMessaging Les détails du message de
notification.
* @return Une chaîne indiquant le résultat de l'envoi de la
notification.
*/
@PostMapping("/send-notification1")
public String sendNotificationByToken(@RequestBody
                                      NotificationPush notificationMessaging) {
return firebaseMessagingService.sendNotificationByToken(notificationMessaging);
}
/**
* Envoie une notification à plusieurs appareils.
*
* @param notificationMessaging Les détails du message denotification.
 * @return Une chaîne indiquant le résultat de l'envoi de la
notification.

@PostMapping("/sendMulticast")
public String sendMultiCastNotification(@RequestBody
                                        NotificationPush notificationMessaging) {
    return     firebaseMessagingService.sendMultiCastNotification(notificationMessaging
            );
}

 */
}