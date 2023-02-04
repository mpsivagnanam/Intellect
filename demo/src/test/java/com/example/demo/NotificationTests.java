package com.example.demo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.constant.Channel;
import com.example.demo.controller.NotificationController;
import com.example.demo.dto.NotificationRequest;
import com.example.demo.dto.NotificationResponse;
import com.example.demo.service.NotificationService;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringJUnit4ClassRunner.class)
public class NotificationTests {
   
    private MockMvc mockMvc;
    
    @Mock
    NotificationService notificationService;
    
    @InjectMocks
    NotificationController notificationController;
    
    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(notificationController).build();
    }
   
	
    @Test
    public void testWhatsAPPMessage () throws Exception {
        NotificationResponse notificationResponse = constructWhatsAPPResponse();
        Mockito.when(notificationService.sendNotification(Mockito.any(NotificationRequest.class))).thenReturn(notificationResponse);
        NotificationRequest notificationRequest = constructWhatsAPPRequest();
        
        String req = new ObjectMapper().writeValueAsString(notificationRequest);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/send")
                .content(req)
                 .header("username", "siva")
                .header("password", "password1")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
       
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
    
    @Test
    public void testWhatsAPPMessageValidation() throws Exception {
        NotificationResponse notificationResponse = constructWhatsAPPResponse();
        Mockito.when(notificationService.sendNotification(Mockito.any(NotificationRequest.class))).thenReturn(notificationResponse);
        NotificationRequest notificationRequest = constructWhatsAPPRequest();
        notificationRequest.setMessage(null);
        String req = new ObjectMapper().writeValueAsString(notificationRequest);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/send")
                .content(req)
                 .header("username", "siva")
                .header("password", "password1")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
       
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.BAD_REQUEST.value(),response.getStatus());
    }
    
    @Test
    public void testSMSMessage() throws Exception {
        NotificationResponse notificationResponse = constructSMSResponse();
        Mockito.when(notificationService.sendNotification(Mockito.any(NotificationRequest.class))).thenReturn(notificationResponse);
        NotificationRequest notificationRequest = constructSMSRequest();
        
        String req = new ObjectMapper().writeValueAsString(notificationRequest);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/send")
                .content(req)
                 .header("username", "siva")
                .header("password", "password1")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
       
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
    
    @Test
    public void testSMSMessageValidation() throws Exception {
        NotificationResponse notificationResponse = constructSMSResponse();
        Mockito.when(notificationService.sendNotification(Mockito.any(NotificationRequest.class))).thenReturn(notificationResponse);
        NotificationRequest notificationRequest = constructSMSRequest();
        notificationRequest.setMessage(null);
        String req = new ObjectMapper().writeValueAsString(notificationRequest);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/send")
                .content(req)
                 .header("username", "siva")
                .header("password", "password1")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
       
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.BAD_REQUEST.value(),response.getStatus());
    }
    
    @Test
    public void testEMAILMessage() throws Exception {
        NotificationResponse notificationResponse = constructEMAILResponse();
        Mockito.when(notificationService.sendNotification(Mockito.any(NotificationRequest.class))).thenReturn(notificationResponse);
        NotificationRequest notificationRequest = constructEMAILRequest();
        
        String req = new ObjectMapper().writeValueAsString(notificationRequest);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/send")
                .content(req)
                 .header("username", "siva")
                .header("password", "password1")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
       
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
    
    @Test
    public void testEmailMessageValidation() throws Exception {
        NotificationResponse notificationResponse = constructEMAILResponse();
        Mockito.when(notificationService.sendNotification(Mockito.any(NotificationRequest.class))).thenReturn(notificationResponse);
        NotificationRequest notificationRequest = constructEMAILRequest();
        notificationRequest.setMessage(null);
        String req = new ObjectMapper().writeValueAsString(notificationRequest);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/send")
                .content(req)
                 .header("username", "siva")
                .header("password", "password1")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
       
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.BAD_REQUEST.value(),response.getStatus());
    }
    
    
    private NotificationResponse constructWhatsAPPResponse() {
        NotificationResponse notificationResponse = new NotificationResponse();
        notificationResponse.setError(null);
        notificationResponse.setResponse("Whatsapp successfully sent");
        notificationResponse.setResponseCode(200);
        return notificationResponse;
    }
    
    private NotificationRequest constructWhatsAPPRequest() {
        NotificationRequest notificationRequest = new NotificationRequest();
        notificationRequest.setChannel(Channel.WHATSAPP);
        notificationRequest.setMessage("Test Whatsapp message");
        List<String> recipientList = new ArrayList<String>();
        recipientList.add("+91999999999");
        recipientList.add("+918888888888");
        notificationRequest.setRecipients(recipientList);
        return notificationRequest;
    }
    
    private NotificationRequest constructSMSRequest() {
        NotificationRequest notificationRequest = new NotificationRequest();
        notificationRequest.setChannel(Channel.SMS);
        notificationRequest.setMessage("Test SMS message");
        List<String> recipientList = new ArrayList<String>();
        recipientList.add("+91999999999");
        recipientList.add("+918888888888");
        notificationRequest.setRecipients(recipientList);
        return notificationRequest;
    }
    
    
    private NotificationResponse constructSMSResponse() {
        NotificationResponse notificationResponse = new NotificationResponse();
        notificationResponse.setError(null);
        notificationResponse.setResponse("SMS successfully sent");
        notificationResponse.setResponseCode(200);
        return notificationResponse;
    }
    
    
    
    
    
    private NotificationResponse constructEMAILResponse() {
        NotificationResponse notificationResponse = new NotificationResponse();
        notificationResponse.setError(null);
        notificationResponse.setResponse("Email successfully sent");
        notificationResponse.setResponseCode(200);
        return notificationResponse;
    }
    
    
    
    private NotificationRequest constructEMAILRequest() {
        NotificationRequest notificationRequest = new NotificationRequest();
        notificationRequest.setChannel(Channel.EMAIL);
        notificationRequest.setMessage("Test Email message");
        List<String> recipientList = new ArrayList<String>();
        recipientList.add("siva@gmail.com");
        recipientList.add("raja@gmail.com");
        notificationRequest.setRecipients(recipientList);
        return notificationRequest;
    }
	
	
	
}
