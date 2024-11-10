package com.boostify.boostify_back.enums;

public enum Status {

    COMPLETED("completed"),
    PENDING("pending"),
    IN_PROGRESS("in_progress");

    private final String value;

    Status(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
    
}
