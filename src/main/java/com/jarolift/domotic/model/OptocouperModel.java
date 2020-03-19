package com.jarolift.domotic.model;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.RaspiPin;

import java.util.ArrayList;

public class OptocouperModel {
    public final static long LONG_PULSE = 4200;
    public final static long SHORT_PULSE = 300;
    public final static long STOP_PULSE = 500;
    public final static int MIN_CHANNEL = 1;
    private final static int MAX_CHANNEL = 8;
    private final static int AVAILABLE_CHANNELS = 2;

    private int currentChannel = MIN_CHANNEL;
    private ArrayList<Integer> allChannels = new ArrayList<>();
    private ArrayList<GpioPinDigitalOutput> pins = new ArrayList<>();
    private GpioPinDigitalOutput pinUp;
    private GpioPinDigitalOutput pinDown;
    private GpioPinDigitalOutput pinStop;
    private GpioPinDigitalOutput pinChangeChannel;

    public OptocouperModel() {
        GpioController gpioController = GpioFactory.getInstance();
        pinDown = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_03);
        pinStop = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_21);
        pinUp = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_22);
        pinChangeChannel = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_25);
        for (int i = MIN_CHANNEL; i <= AVAILABLE_CHANNELS; i++) {
            allChannels.add(i);
        }
    }

    public GpioPinDigitalOutput getPinChangeChannel() {
        return pinChangeChannel;
    }

    public GpioPinDigitalOutput getPinStop() {
        return pinStop;
    }

    public int getCurrentChannel() {
        return currentChannel;
    }

    public GpioPinDigitalOutput getPinByButton(String button) {
        if (button.equals("up")){
            return pinUp;
        } else if (button.equals("down")){
            return pinDown;
        } else if (button.equals("stop")) {
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

    public void increaseChannel() {
        currentChannel++;
        if (currentChannel == (MAX_CHANNEL + 1)) {
            currentChannel = MIN_CHANNEL;
        }
    }
}
