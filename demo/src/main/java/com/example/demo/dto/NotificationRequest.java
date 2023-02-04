package com.example.demo.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.example.demo.constant.Channel;

/**
 * This is a DTO class to accept the request from API 
 */
public class NotificationRequest {

    
    @NotBlank(message = "Message is required.")
    @Size(min = 2, max = 256, message = "The length of Message must be between 2 and 256 characters.")
    private String message;
    
    @NotEmpty(message = "Recipients is required.")
    private List<@NotEmpty(message = "Recipients value should not be empty") String> recipients;
    
    @NotNull(message = "Channel is required and it should be EMAIL/SMS/WHATSAPP")
    private Channel channel;
    
    private String username;
    private String password;
    
    /**
     * @return
     */
    public String getMessage() {
        return message;
    }
    /**
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }
    
    /**
     * @return
     */
    public List<String> getRecipients() {
        return recipients;
    }
    /**
     * @param recipients
     */
    public void setRecipients(List<String> recipients) {
        this.recipients = recipients;
    }
    /**
     * @return
     */
    public Channel getChannel() {
        return channel;
    }
    /**
     * @param channel
     */
    public void setChannel(Channel channel) {
        this.channel = channel;
    }
    /**
     * @return
     */
    public String getUsername() {
        return username;
    }
    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
     * @return
     */
    public String getPassword() {
        return password;
    }
    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
