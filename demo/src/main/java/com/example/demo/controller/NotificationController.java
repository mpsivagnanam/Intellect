package com.example.demo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.NotificationRequest;
import com.example.demo.dto.NotificationResponse;
import com.example.demo.service.NotificationService;

/**
 * 
 * This class used to send all type of notifications
 * 
 */
@Tag(name = "Send notification api", description = "To send sms, email, whatsapp notification  operation")
@RestController
@RequestMapping("send")
@CrossOrigin(origins="*")
public class NotificationController {
    
    @Autowired
    NotificationService notificationService;

    /**
     * This method used to send notification by accepting NotificationRequest and return the NotificationResponse
     * @param user
     * @return
     */
    @Operation(summary = "Send Notification", description = "Send notification based on channel", tags = { "notification" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = NotificationRequest.class))),
        @ApiResponse(responseCode = "400", description = "Invalid ID supplied", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized API", content = @Content),
        @ApiResponse(responseCode = "500", description = "Notification Error", content = @Content),
        @ApiResponse(responseCode = "404", description = "Book not found", content = @Content) })
    @PostMapping
    public ResponseEntity<NotificationResponse> sendNotification(@Valid @RequestBody NotificationRequest notificationRequest, 
                                                                 @RequestHeader(name="username")    String username,
                                                                 @RequestHeader("password") String password ){
        notificationRequest.setUsername(username);
        notificationRequest.setPassword(password);
        NotificationResponse notificationResponse = notificationService.sendNotification(notificationRequest);
        return new ResponseEntity<NotificationResponse>(notificationResponse, HttpStatus.OK);
        
    }
}
