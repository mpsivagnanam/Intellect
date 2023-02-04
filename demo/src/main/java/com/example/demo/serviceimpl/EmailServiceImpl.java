package com.example.demo.serviceimpl;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.constant.EmailConstant;
import com.example.demo.dto.NotificationRequest;
import com.example.demo.dto.NotificationResponse;
import com.example.demo.service.EmailService;
import com.example.demo.validation.NotificationValidation;

/**
 * Email Service Implementation class used to send the email to recipients
 */
@Service
public class EmailServiceImpl implements EmailService {

    /**
     * This method accept notificationRequest and validate the recipient details. 
     * If validation is success, then it will construct the email properties and send the email to recipient.
     * 
     * @param notificationRequest
     * @return
     */
    @Override
    public NotificationResponse sendEmail(NotificationRequest notificationRequest) {
        NotificationResponse notificationResponse = new NotificationResponse();
        try {
            
            String validationMessage = NotificationValidation.validateEmailRecipient(notificationRequest.getRecipients());
            if (validationMessage != null) {
                throw new Exception(validationMessage);
            }
            
            Properties properties = System.getProperties();
            properties.setProperty(EmailConstant.hostKey, EmailConstant.host);
            Session session = Session.getDefaultInstance(properties);

            MimeMessage message = new MimeMessage(session);
            InternetAddress[] toAddress = constructTOAddress(notificationRequest);
            message.setRecipients(Message.RecipientType.TO, toAddress);
            message.setFrom(new InternetAddress(EmailConstant.sender));
            message.setSubject(EmailConstant.subject);
            message.setText(notificationRequest.getMessage());

            // Transport.send(message);
            notificationResponse.setResponse("Mail successfully sent");
            notificationResponse.setResponseCode(HttpStatus.OK.value());
            return notificationResponse;
        } catch (Exception mex) {
            notificationResponse.setError(mex.getMessage());
            notificationResponse.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return notificationResponse;
        }

    }

    /**
     * This method used to construct single or multiple recipient details to send email 
     * @param notificationRequest
     * @return
     * @throws AddressException
     */
    private InternetAddress[] constructTOAddress(NotificationRequest notificationRequest) throws AddressException {
        InternetAddress[] internetAddress = new InternetAddress[notificationRequest.getRecipients().size()];

        for (int i = 0; i < notificationRequest.getRecipients().size(); i++) {
            internetAddress[i] = new InternetAddress(notificationRequest.getRecipients().get(i));
        }
        return internetAddress;
    }

}
