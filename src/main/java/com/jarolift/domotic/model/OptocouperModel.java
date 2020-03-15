package com.jarolift.domotic.model;

import com.pi4j.io.gpio.*;

import java.util.ArrayList;

public class OptocouperModel {
    private ArrayList<Integer> allChannels = new ArrayList<>();
    private ArrayList<GpioPinDigitalOutput> pins = new ArrayList<>();
    private GpioPinDigitalOutput pinUp;
    private GpioPinDigitalOutput pinDown;
    private GpioPinDigitalOutput pinStop;
    private GpioPinDigitalOutput pinChangeChannel;

    public OptocouperModel() {
        GpioController gpioController = GpioFactory.getInstance();
        pinUp = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_00);
        pinDown = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_01);
        pinStop = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_02);
        pinChangeChannel = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_03);
        allChannels.add(1);
        allChannels.add(2);
        pins.add(pinUp);
        pins.add(pinDown);
        pins.add(pinStop);
        pins.add(pinChangeChannel);
    }

    public ArrayList<GpioPinDigitalOutput> getPins() {
        return pins;
    }

    public GpioPinDigitalOutput getPinChangeChannel() {
        return pinChangeChannel;
    }

    public GpioPinDigitalOutput getPinStop() {
        return pinStop;
    }

    public GpioPinDigitalOutput getPinByButton(String button) {
        if (button == "up"){
            return pinUp;
        } else if (button == "down"){
            return pinDown;
        } else if (button == "stop") {
            return pinStop;
        }

        System.out.println("Error '" + button + "' button not valid, using 'STOP' button by default");
        return pinStop;
    }

    public ArrayList<Integer> getArrayChannels(int channel) {
        if (channel == 0) {
            return allChannels;
        } else {
            ArrayList<Integer> channels = new ArrayList<>();
            channels.add(channel);
            return channels;
        }
    }
}
