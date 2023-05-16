package com.apiRegion.springjwt.models;


import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Component;

@Component
public class SmsSender {
    //Find your Account Sid and Token at twilio.com/console
    public static final String ACCOUNT_SID = "ACf37f35484eba83ecc0654ef515aa6779";
    public static final String AUTH_TOKEN = "527649eb86353772fe9aea33643eb650";

    public void send(String phoneNumber, String message) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message sms = Message.creator(new PhoneNumber(phoneNumber),
                new PhoneNumber("+1 929 556 7186"), message).create();
    }


}