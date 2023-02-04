package com.example.demo.validation;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Notification validation class to validate the request
 */
public class NotificationValidation {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static final Pattern VALID_PHONE_NUMBER_REGEX = Pattern.compile("(0/91)?[7-9][0-9]{9}", Pattern.CASE_INSENSITIVE);
   
    /**
     * This method will validate the recipient having valid email or not using regex.
     * @param recipientList
     * @return
     */
    public static String validateEmailRecipient(List<String> recipientList) {
        for (String recipient : recipientList) {
            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(recipient);
            if (!matcher.find()) {
                return "Email id format is invalid for recipient :: " + recipient;
            }
        }

        return null;
    }
    
    
    /**
     * This method will validate the recipient having valid phone number or not using regex.
     * @param recipientList
     * @return
     */
    public static String validatePhoneNoRecipient(List<String> recipientList) {
        for (String recipient : recipientList) {
            Matcher matcher = VALID_PHONE_NUMBER_REGEX.matcher(recipient);
            if (!matcher.find()) {
                return "Phone number format is invalid for recipient :: " + recipient;
            }
        }

        return null;
    }

}
