package com.jarolift.domotic.model;

public class BlindState {
    private static int INITIAL_STATE = 100;
    private int percentage;
    private int channel;

    public BlindState(int channel) {
        this.percentage = INITIAL_STATE;
        this.channel = channel;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }
}
