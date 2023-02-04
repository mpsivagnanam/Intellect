package com.example.demo.Exception;

import java.util.List;

import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.dto.NotificationResponse;
/**
 * This class used to process the expection response and send it to API
 */
@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler{
    
    /**
     * This method used to process the expection using java x validation class
     * 
     * {@inheritDoc}
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException ex, HttpHeaders headers,
        HttpStatus status, WebRequest request) {
      NotificationResponse notificationResponse = new NotificationResponse();
      List<String> errors = ex.getBindingResult()
          .getFieldErrors()
          .stream()
          .map(DefaultMessageSourceResolvable::getDefaultMessage)
          .collect(Collectors.toList());

      notificationResponse.setError(errors.toString());
      notificationResponse.setResponseCode(HttpStatus.BAD_REQUEST.value());

      return new ResponseEntity<>(notificationResponse, HttpStatus.BAD_REQUEST);
    }
    
    /**
     * This method used to process the generic expection and also enum exception using java x validation class
     * {@inheritDoc}
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException exception,
                                                               HttpHeaders headers, HttpStatus status, WebRequest request) {
        String genericMessage = "Request parameter is not available in request ";
        if(exception.getMessage().contains("Enum")){
            genericMessage = "Channel is required and it should be EMAIL/SMS/WHATSAPP only";
        }
        NotificationResponse notificationResponse = new NotificationResponse();
        notificationResponse.setError(genericMessage);
        notificationResponse.setResponseCode(HttpStatus.BAD_REQUEST.value());
        return handleExceptionInternal(exception, notificationResponse, headers, HttpStatus.BAD_REQUEST, request);
    }

    
}
