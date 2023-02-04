package com.example.demo.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.NotificationRequest;
import com.example.demo.dto.NotificationResponse;
import com.example.demo.service.EmailService;
import com.example.demo.service.NotificationService;
import com.example.demo.service.SMSService;
import com.example.demo.service.WhatsappService;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    EmailService emailService;

    @Autowired
    SMSService smsService;

    @Autowired
    WhatsappService whatsappService;

    /**
     * 
     *  This method accept notification request and based on channel it will call 
     *  respective notification service
     *  @param notificationRequest
     *  @return notificationResponse
     *  
     */
    @Override
    public NotificationResponse sendNotification(NotificationRequest notificationRequest) {

        switch (notificationRequest.getChannel()) {

        case EMAIL:
            return emailService.sendEmail(notificationRequest);

        case SMS:
            return smsService.sendSMS(notificationRequest);

        case WHATSAPP:
            return whatsappService.sendWhasApp(notificationRequest);
        default:
            NotificationResponse notificationResponse = new NotificationResponse();
            notificationResponse.setError("NO CHANNEL");
            notificationResponse.setResponseCode(500);
            return notificationResponse;

        }
    }

}
