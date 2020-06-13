package com.jarolift.domotic.model;

public class RequestModel {
    private static final String MIDDLE_BUTTON = "middle";
    private String button;
    private String userAgent;
    private int channel;

    public RequestModel(String button, int channel, String userAgent) {
        this.button = button;
        this.channel = channel;
        this.userAgent = userAgent;
    }

    public RequestModel(int channel, String userAgent) {
        this(MIDDLE_BUTTON, channel, userAgent);
    }

    public String getButton() {
        return button;
    }

    public int getChannel() {
        return channel;
    }

    public boolean isMiddleButton() {
        return button.equals(MIDDLE_BUTTON);
    }

    public String getUserAgent() {
        return userAgent;
    }
}
