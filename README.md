# Intellect Design
Intellect Design java tech lead assignment

## Excersice : 1 Java problem

## Notification Application

### Problem Statement

Create a Notification Dispatcher. Create an Rest endpoint that allows the user to provide 3 inputs:
A notification message
A recipient or recipients
A channel of notification - Email, SMS, WhatsApp
Assume credentials are available in request header. Application identifies the appropriate channel adapter and sends out the notification to the intended recipients

### Introduction

Created notification application to send email, sms and whatsapp notification to recipient.

### Technology Used

1. Spring boot 2.7 
2. Java 8
3. Twilio library
4. Maven
5. Mockito 
6. Open API Documentation

### API Details

Created common api to send all the notification. Based on the channel respective notification will be trigger

**API URL : http://localhost:8080/send**

**Method : Post**

**Request :** 

{
      "message": "This is test message ", // Notification message  
      "recipients": ["+91999999999","+91888888888"], //recipient list 
      "channel": "SMS/EMAIL/WHATSAPP" // Select any one Channel 
}  

**Response :**

{
    "responseCode": 200, //Response code 200 or 500 or 400
    "response": "SMS successfully sent", // Response status
    "error": null // Error Response
}

### Documentation

Open doc api is created to use api effectively.

**Swagger URL :  http://localhost:8080/swagger-ui/index.html#/ **


### Implementation

**NotificationController:** Used to accept the notification request and process the notification response.

**NotificationService:** Used to process the channel from incoming request and call the respective notification channel service

**EmailService:** Email service class will validate the incoming request and then construct the email request and send email to intend recipient.

**SMSService:** SMS Service class also will validate the incoming request and construct the request for MSG91 api and send the request to MSG91 API.

**WhatsAppService:** WhatsApp service class will validate the phone number format. If the phone number is valid then it will construct the request for TWILIO api and send the request to TWILIO api.

**ExceptionHandler:** Used to process the exception and send the error response

**NotificationTests:** This class contains the unit testing

### Validation

Request validation using **javax validator** and recipient validation using **regex**

### Test

We have cover unit testing using **mockito** framework and cover positive and negative scenario

### Build

TO build the project use below command

**mvn clean install**

To run the application use below command

**java -jar <JAR_NAME>.jar**


## Excersice 2: SQL Problem

Acme Bank Customers can each have multiple Accounts. Acme bank has 1Million+ customers. Each customer can have any number of accounts.

1.Write an SQL query to find out the top 3 High Net worth Customers of Acme bank.

**SELECT CUSTOMER_ID FROM ( SELECT CUSTOMER_ID, 
SUM (ACCOUNT_BALANCE) AS AMOUNT
FROM ACCOUNT_MASTER
GROUP BY CUSTOMER_ID ORDER BY AMOUNT DESC LIMIT 0,3 );**

2.Suggest options/techniques to improve model and query performance

**Indexing:** It will increase the speed so create Index on CUSTOMER_ID

**Query Optimization :** Avoid using wild card *, we need to use column name

**Batch Process:** To process the huge data, use batch process.  

**Cache:** Use cache for frequently using queries

**Avoid sub queries:** Multiple sub queries using where will reduce the performance

**Data type:** Appropriate data type and its length

**Pagination:** Use pagination it will not scan the database fully 
