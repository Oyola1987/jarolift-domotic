package com.jarolift.domotic.service;

import com.jarolift.domotic.model.OptocouperModel;
import com.jarolift.domotic.model.RequestModel;
import com.pi4j.io.gpio.GpioPinDigitalOutput;

public class OptocouperService {
    static private int DEFAULT_CHANNEL = 1;
    private OptocouperModel optocouperModel;
    private int currentChannel = DEFAULT_CHANNEL;

    public OptocouperService() {
        optocouperModel = new OptocouperModel();
        optocouperModel.getPins().forEach((pin) -> pin.low());
    }

    public void pushed(RequestModel requestModel) {
        GpioPinDigitalOutput pin = optocouperModel.getPinByButton(requestModel.getButton());

        optocouperModel.getArrayChannels(requestModel.getChannel()).forEach((n) -> {
            System.out.println("Set pin to HIGH, channel: " + n + ", button: " + requestModel.getButton());
            pin.high();
        });
    }

    public void unPushed(RequestModel requestModel) {
        GpioPinDigitalOutput pin = optocouperModel.getPinByButton(requestModel.getButton());

        optocouperModel.getArrayChannels(requestModel.getChannel()).forEach((n) -> {
            System.out.println("Set pin to LOW, channel: " + n + ", button: " + requestModel.getButton());
            pin.low();
        });
    }

    public void pushedMiddle(RequestModel requestModel) {
        GpioPinDigitalOutput stopPin = optocouperModel.getPinStop();

        optocouperModel.getArrayChannels(requestModel.getChannel()).forEach((n) -> {
            System.out.println("Set pin to HIGH, channel: " + n + ", button: middle");
            stopPin.high();
        });
    }

    private void selectChannel(int channel) {
        while (currentChannel != channel) {
            increaseChannel();
        }
    }

    private void selectDefaultChannel() {
        while (currentChannel != DEFAULT_CHANNEL) {
            increaseChannel();
        }
    }

    private void increaseChannel() {
        GpioPinDigitalOutput changePin = optocouperModel.getPinChangeChannel();
        changePin.pulse(200);
        currentChannel ++;
        if (currentChannel == 9) {
            currentChannel = DEFAULT_CHANNEL;
        }
    }
}
