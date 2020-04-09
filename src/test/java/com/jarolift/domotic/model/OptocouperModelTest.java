package com.jarolift.domotic.model;

import com.pi4j.io.gpio.GpioController;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OptocouperModelTest {
    @Mock
    private GpioController gpioController;

    @Test
    public void increaseChannel() {
        OptocouperModel optocouperModel = new OptocouperModel(new ConsolePulsableFactory());
        optocouperModel.increaseChannel();
        assertEquals(2, optocouperModel.getCurrentChannel());
    }
}