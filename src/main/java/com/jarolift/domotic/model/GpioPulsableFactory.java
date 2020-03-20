package com.jarolift.domotic.model;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.RaspiPin;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("production")
public class GpioPulsableFactory implements PulsableFactory {
    private final GpioController gpioController = GpioFactory.getInstance();

    @Override
    public Pulsable getPinDown() {
        return new GpioPulsable(gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_03));
    }

    @Override
    public Pulsable getPinStop() {
        return new GpioPulsable(gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_21));
    }

    @Override
    public Pulsable getPinUp() {
        return new GpioPulsable(gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_22));
    }

    @Override
    public Pulsable getPinChangeChannel() {
        return new GpioPulsable(gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_25));
    }
}
