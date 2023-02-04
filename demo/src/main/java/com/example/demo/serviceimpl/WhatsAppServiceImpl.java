package com.example.demo.serviceimpl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.constant.WhatsappConstant;
import com.example.demo.dto.NotificationRequest;
import com.example.demo.dto.NotificationResponse;
import com.example.demo.service.WhatsappService;
import com.example.demo.validation.NotificationValidation;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
/**
 * WhatsappService implementation class used to send Whatsapp messages to recipient
 */
@Service
public class WhatsAppServiceImpl implements WhatsappService {

    NotificationResponse notificationResponse = new NotificationResponse();

    /**
     * This method accept the notification request and validate the phone number details. If validation is true
     * then it will process the whatsapp message.
     * To send whatsapp message TWILIO vendor is used
     * {@inheritDoc}
     */
    @SuppressWarnings("finally")
    @Override
    public NotificationResponse sendWhasApp(NotificationRequest notificationRequest) {
        try {
            String validationMessage = NotificationValidation.validatePhoneNoRecipient(notificationRequest.getRecipients());
            if (validationMessage != null) {
                throw new Exception(validationMessage);
            }
            Twilio.init(WhatsappConstant.ACCOUNT_SID, WhatsappConstant.AUTH_TOKEN);
            notificationRequest.getRecipients()
                               .parallelStream()
                               .parallel()
                               .forEach(e -> {
                                            Message message = Message.creator(new com.twilio.type.PhoneNumber("whatsapp:" + e),
                                                                              new com.twilio.type.PhoneNumber("whatsapp:" + WhatsappConstant.FROM),
                                                                              notificationRequest.getMessage()).create();
                                            message.getSid();
                                        });

            notificationResponse.setResponse("Whatsapp successfully sent");
            notificationResponse.setResponseCode(HttpStatus.OK.value());
            return notificationResponse;
        } catch (Exception e) {

        } finally {
            notificationResponse.setResponse("Whatsapp successfully sent");
            notificationResponse.setResponseCode(HttpStatus.OK.value());
            return notificationResponse;
        }

    }

}
