package com.jarolift.domotic.model;

public class ResponseModel {
    String message;
    String button;
    int channel;

    public ResponseModel(String message, String button, int channel) {
        this.message = message;
        this.button = button;
        this.channel = channel;
    }

    public String getMessage() {
        return message;
    }

    public String getButton() {
        return button;
    }

    public int getChannel() {
        return channel;
    }
}
