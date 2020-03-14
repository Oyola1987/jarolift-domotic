package com.jarolift.domotic.service;

import com.jarolift.domotic.model.RequestModel;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.RaspiPin;

public class OptocouperService {
    private GpioPinDigitalOutput pinUp;
    private GpioPinDigitalOutput pinDown;
    private GpioPinDigitalOutput pinStop;
    private GpioPinDigitalOutput pinChangeChannel;

    public OptocouperService() {
        GpioController gpioController = GpioFactory.getInstance();

        pinUp = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_00);
        pinDown = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_01);
        pinStop = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_02);
        pinChangeChannel = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_03);

        pinUp.low();
        pinDown.low();
        pinStop.low();
        pinChangeChannel.low();
    }

    public void updateState(RequestModel requestModel) {
        GpioPinDigitalOutput pin = getPin(requestModel);

        if (requestModel.getEvent() == "down") {
            pin.high();
        } else {
            pin.low();
        }
    }

    public void updateMiddle(RequestModel requestModel) {
        int channel = requestModel.getChannel();

    }

    private GpioPinDigitalOutput getPin(RequestModel requestModel) {
        String button = requestModel.getButton();

        if (button == "up"){
           return pinUp;
        }else if (button == "down"){
           return pinDown;
        }

        return pinStop;
    }
}
