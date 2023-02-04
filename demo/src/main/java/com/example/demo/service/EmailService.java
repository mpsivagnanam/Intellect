package com.example.demo.service;

import com.example.demo.dto.NotificationRequest;
import com.example.demo.dto.NotificationResponse;

public interface EmailService {

    /**
     * 
     * @param notificationRequest
     * @return
     */
    public NotificationResponse sendEmail(NotificationRequest notificationRequest);
}
