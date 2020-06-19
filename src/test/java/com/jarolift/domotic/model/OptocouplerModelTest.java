package com.jarolift.domotic.model;

import com.jarolift.domotic.service.StorageChannel;
import com.pi4j.io.gpio.GpioController;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OptocouplerModelTest {
    @Mock
    private GpioController gpioController;

    @Test
    public void increaseChannel() {
        OptocouplerModel optocouplerModel = new OptocouplerModel(new ConsolePulsableFactory(), new StorageChannel());
        optocouplerModel.increaseChannel();
        assertEquals(2, optocouplerModel.getCurrentChannel());
    }
}