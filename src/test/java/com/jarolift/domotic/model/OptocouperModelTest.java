package com.jarolift.domotic.model;

import com.pi4j.io.gpio.GpioController;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OptocouperModelTest {
    @Mock
    private GpioController gpioController;

    @org.junit.Test
    public void increaseChannel() {
        OptocouperModel optocouperModel = new OptocouperModel(new ConsolePulsableFactory());
        optocouperModel.increaseChannel();
        Assert.assertEquals(2,optocouperModel.getCurrentChannel());
    }
}