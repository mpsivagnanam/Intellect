package com.example.demo.service;

import com.example.demo.dto.NotificationRequest;
import com.example.demo.dto.NotificationResponse;

public interface SMSService {
    /**
     * 
     * @param notificationRequest
     * @return
     */
    public NotificationResponse sendSMS(NotificationRequest notificationRequest);

}
