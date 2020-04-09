package com.jarolift.domotic.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.nashorn.internal.ir.annotations.Ignore;

public class RequestModel {
    private static final String MIDDLE_BUTTON = "middle";
    private String button;
    private int channel;

    public RequestModel(String button, int channel) {
        this.button = button;
        this.channel = channel;
    }

    public RequestModel(int channel) {
        this(MIDDLE_BUTTON, channel);
    }

    public String getButton() {
        return button;
    }

    public int getChannel() {
        return channel;
    }

    @JsonIgnore
    public boolean isMiddleButton() {
        return button.equals(MIDDLE_BUTTON);
    }
}
