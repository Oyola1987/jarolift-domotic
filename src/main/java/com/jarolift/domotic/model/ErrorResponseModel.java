package com.jarolift.domotic.model;

public class ErrorResponseModel extends ResponseModel {
    String message;

    public ErrorResponseModel(String message, String button, int channel) {
        super(button, channel);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
