package com.jarolift.domotic.model;

public class ResponseModel {
    String button;
    int channel;

    public ResponseModel(String button, int channel) {
        this.button = button;
        this.channel = channel;
    }

    public String getButton() {
        return button;
    }

    public int getChannel() {
        return channel;
    }
}
