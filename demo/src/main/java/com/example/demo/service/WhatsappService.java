package com.example.demo.service;

import com.example.demo.dto.NotificationRequest;
import com.example.demo.dto.NotificationResponse;

public interface WhatsappService {

    /**
     * 
     * @param notificationRequest
     * @return
     */
    public NotificationResponse sendWhasApp(NotificationRequest notificationRequest);
}
