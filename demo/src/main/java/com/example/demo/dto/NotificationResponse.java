package com.example.demo.dto;
/**
 * This DTO class used to send the response to API
 */
public class NotificationResponse {
    
    private Integer responseCode;
    private String response;
    private String error;
    
    /**
     * @return
     */
    public Integer getResponseCode() {
        return responseCode;
    }
    /**
     * @param responseCode
     */
    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }
    /**
     * @return
     */
    public String getResponse() {
        return response;
    }
    /**
     * @param response
     */
    public void setResponse(String response) {
        this.response = response;
    }
    /**
     * @return
     */
    public String getError() {
        return error;
    }
    /**
     * @param error
     */
    public void setError(String error) {
        this.error = error;
    }
    
    

}
