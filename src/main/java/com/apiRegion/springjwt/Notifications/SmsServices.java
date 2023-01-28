package com.apiRegion.springjwt.Notifications;

import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


@Component
public class SmsServices {

	    
	    private final String ACCOUNT_SID ="ACf37f35484eba83ecc0654ef515aa6779";

	    private final String AUTH_TOKEN = "6dde41ae0d6ca15e0c643383e73bf0a1";

	    private final String FROM_NUMBER = "+19295567186";

	    public void send(SmsPojo sms) {
	    	Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

	        Message message = Message.creator(new PhoneNumber(sms.getTo()), new PhoneNumber(FROM_NUMBER), sms.getMessage())
	                .create();
	        System.out.println("here is my id:"+message.getSid());// Unique resource ID created to manage this transaction

	    }

	    public void receive(MultiValueMap<String, String> smscallback) {
	    }
	
}