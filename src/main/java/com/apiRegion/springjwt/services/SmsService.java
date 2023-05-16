package com.apiRegion.springjwt.services;

import com.apiRegion.springjwt.models.SmsSender;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class SmsService {
    @Autowired
    private SmsSender smsSender;

    public void sendSms(String phoneNumber, String message) {
        smsSender.send(phoneNumber, message);
    }
}