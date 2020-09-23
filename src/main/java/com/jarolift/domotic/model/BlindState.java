package com.jarolift.domotic.model;


public class BlindState {
    private int percentage;
    private int channel;
    private float seconds;
    private String name;

    public BlindState(String name, int channel, int percentage, float seconds) {
        this.percentage = percentage;
        this.channel = channel;
        this.seconds = seconds;
        this.name = name;
    }

    public int getPercentage() {
        return percentage;
    }

    public int getChannel() {
        return channel;
    }

    public String getName() {
        return name;
    }
}
