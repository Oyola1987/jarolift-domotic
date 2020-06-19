package com.jarolift.domotic.service;

public enum PulseDuration {
    SHORT_PULSE(500),
    LONG_PULSE(4000);

    public long duration;

    PulseDuration(long duration) {
        this.duration = duration;
    }
}
