package com.jarolift.domotic.model;

public class RequestModel {
    private String button;
    private int channel;

    public RequestModel(String button, int channel) {
        this.button = button;
        this.channel = channel;
    }

    public RequestModel(int channel) {
        this.channel = channel;
    }

    public String getButton() {
        return button;
    }

    public int getChannel() {
        return channel;
    }

    @Override
    public String toString() {
        return "RequestModel{" +
                ", button='" + button + '\'' +
                ", channel=" + channel +
                '}';
    }

}
