package com.example.demo.service;

import com.example.demo.dto.NotificationRequest;
import com.example.demo.dto.NotificationResponse;

public interface NotificationService {
    /**
     * 
     * @param notificationRequest
     * @return
     */
    public NotificationResponse sendNotification(NotificationRequest notificationRequest);

}
