package com.jarolift.domotic.model;

public interface PulsableFactory {
    Pulsable getPinDown();
    Pulsable getPinStop();
    Pulsable getPinUp();
    Pulsable getPinChangeChannel();
}
