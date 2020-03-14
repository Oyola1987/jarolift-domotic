package com.jarolift.domotic.model;

public class RequestModel {
    private String event;
    private String button;
    private int channel;

    public RequestModel(String event, String button, int channel) {
        this.event = event;
        this.button = button;
        this.channel = channel;
    }

    public RequestModel() {
    }

    public RequestModel(int channel) {
        this.channel = channel;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public void setButton(String button) {
        this.button = button;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

    public String getEvent() {
        return event;
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
                "event='" + event + '\'' +
                ", button='" + button + '\'' +
                ", channel=" + channel +
                '}';
    }

}
