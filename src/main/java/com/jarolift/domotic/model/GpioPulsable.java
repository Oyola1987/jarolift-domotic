package com.jarolift.domotic.model;

import com.pi4j.io.gpio.GpioPinDigitalOutput;

import java.util.concurrent.Future;

public class GpioPulsable implements Pulsable {
    private final GpioPinDigitalOutput gpioPinDigitalOutput;

    public GpioPulsable(GpioPinDigitalOutput gpioPinDigitalOutput) {
        this.gpioPinDigitalOutput = gpioPinDigitalOutput;
    }

    @Override
    public Future<?> pulse(long time) {
        return gpioPinDigitalOutput.pulse(time);
    }
}
