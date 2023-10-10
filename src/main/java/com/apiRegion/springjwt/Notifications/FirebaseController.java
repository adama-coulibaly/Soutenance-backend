package com.apiRegion.springjwt.Notifications;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class FirebaseController {


    private final FireBaseService fireBaseService;

    public FirebaseController(FireBaseService fireBaseService) {
        this.fireBaseService = fireBaseService;
    }

    @PostMapping
    public String sendNotificationByToken(@RequestBody SendNotification sendNotification){
        return  fireBaseService.SendNotification(sendNotification);
    }
}



