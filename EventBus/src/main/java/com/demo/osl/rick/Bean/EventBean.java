package com.demo.osl.rick.Bean;

public class EventBean {
    protected String message;

    public  EventBean(String message){
        this.message=message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
