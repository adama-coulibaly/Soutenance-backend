package com.apiRegion.springjwt.Notifications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class FirebaseController {

    @Autowired
    private FireBaseService fireBaseService;

    @PostMapping
    public String sendNotificationByToken(@RequestBody SendNotification sendNotification){
        return  fireBaseService.SendNotification(sendNotification);
    }
}
