package com.apiRegion.springjwt.Notifications;

import lombok.Data;

import java.util.Map;

@Data
public class SendNotification {

    private  String Token;
    private  String Titre;
    private  String Contenu;
    private  String Image;
    private Map<String,String> data;

}
