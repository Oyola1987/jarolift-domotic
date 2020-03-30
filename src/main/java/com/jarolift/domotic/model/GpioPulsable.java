package com.jarolift.domotic.model;

import com.pi4j.io.gpio.GpioPinDigitalOutput;

public class GpioPulsable implements Pulsable {
    private final GpioPinDigitalOutput gpioPinDigitalOutput;
    public static final long WAIT_TO_PULSE = 500;

    public GpioPulsable(GpioPinDigitalOutput gpioPinDigitalOutput) {
        this.gpioPinDigitalOutput = gpioPinDigitalOutput;
    }

    @Override
    public void pulse(long time) {
        try {
            gpioPinDigitalOutput.high();
            Thread.sleep(time);
            gpioPinDigitalOutput.low();
            Thread.sleep(WAIT_TO_PULSE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
