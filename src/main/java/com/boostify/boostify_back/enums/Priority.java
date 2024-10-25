package com.boostify.boostify_back.enums;

public enum Priority {
    
    LOW("low"),
    HIGH("high"),
    AVERAGE("average");

    private final String value;

    Priority(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }
    
}
