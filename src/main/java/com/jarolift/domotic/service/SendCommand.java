package com.jarolift.domotic.service;

import com.jarolift.domotic.model.RequestModel;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.RaspiPin;

public class SendCommand {
    public void update(RequestModel requestModel) {
        /*GpioController gpioController = GpioFactory.getInstance();
        GpioPinDigitalOutput up = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_00);
        up.high();*/
        System.out.println(requestModel.toString());
    }
}
