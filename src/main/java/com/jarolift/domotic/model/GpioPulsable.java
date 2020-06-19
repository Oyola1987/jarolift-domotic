package com.jarolift.domotic.model;

import com.jarolift.domotic.service.PulseDuration;
import com.pi4j.io.gpio.GpioPinDigitalOutput;

public class GpioPulsable implements Pulsable {
    private final GpioPinDigitalOutput gpioPinDigitalOutput;

    public GpioPulsable(GpioPinDigitalOutput gpioPinDigitalOutput) {
        this.gpioPinDigitalOutput = gpioPinDigitalOutput;
    }

    @Override
    public void pulse(PulseDuration time) {
        try {
            gpioPinDigitalOutput.high();
            Thread.sleep(time.duration);
            gpioPinDigitalOutput.low();
            Thread.sleep(PulseDuration.SHORT_PULSE.duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
