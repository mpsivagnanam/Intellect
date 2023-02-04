package com.example.demo.serviceimpl;

import java.net.URL;
import java.net.URLConnection;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.constant.SMSConstant;
import com.example.demo.dto.NotificationRequest;
import com.example.demo.dto.NotificationResponse;
import com.example.demo.service.SMSService;
import com.example.demo.validation.NotificationValidation;
/**
 * SMSService Implementation class used to send SMS to recipients.
 * 
 */
@Service
public class SMSServiceImpl implements SMSService{

    /**
     * 
     * This method will validate the incoming recipient phone number. If valitaion is success
     * then it will construct the sms recipient format and send sms to recipient
     * 
     * To send sms MSG91 vendor is used 
     * 
     * @param
     * 
     */
    @Override
    public NotificationResponse sendSMS(NotificationRequest notificationRequest) {
        NotificationResponse notificationResponse = new NotificationResponse();
        try {
            String validationMessage = NotificationValidation.validatePhoneNoRecipient(notificationRequest.getRecipients());
            if (validationMessage != null) {
                throw new Exception(validationMessage);
            }
            
            StringBuilder sbPostData = constructSMSRequest(notificationRequest);
            URL myURL = null;
            
            myURL = new URL(SMSConstant.msg91URL+sbPostData.toString());
            URLConnection myURLConnection = myURL.openConnection();
            myURLConnection.connect();
            notificationResponse.setResponse("SMS successfully sent");
            notificationResponse.setResponseCode(HttpStatus.OK.value());
            return notificationResponse;
        } catch (Exception e) {
            notificationResponse.setError(e.getMessage());
            notificationResponse.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return notificationResponse;
        }
        
    }

    /**
     * This method used to construct MSG91 api request format
     * @param notificationRequest
     * @return
     */
    private StringBuilder constructSMSRequest(NotificationRequest notificationRequest) {
        StringBuilder sbPostData= new StringBuilder(SMSConstant.msg91URL);
        sbPostData.append("authkey="+SMSConstant.authkey);
        sbPostData.append("&mobiles="+notificationRequest.getRecipients());
        sbPostData.append("&message="+notificationRequest.getMessage());
        sbPostData.append("&route="+SMSConstant.route);
        sbPostData.append("&sender="+SMSConstant.senderId);
        return sbPostData;
    }

}
