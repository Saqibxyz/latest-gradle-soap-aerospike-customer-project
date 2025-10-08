package com.rest.demo.dto;

/**
 * This is for response of creating many customers
 * this has id of the customers which were successfully saved to database
 */
public class ListCreateDTO {
    String id;
    String status;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
