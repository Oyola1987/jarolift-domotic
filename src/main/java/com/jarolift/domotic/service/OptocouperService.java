package com.jarolift.domotic.service;

import com.jarolift.domotic.model.OptocouperModel;
import com.jarolift.domotic.model.RequestModel;
import com.pi4j.io.gpio.GpioPinDigitalOutput;

public class OptocouperService {
    private OptocouperModel optocouperModel;

    public OptocouperService() {
        optocouperModel = new OptocouperModel();
    }

    public void pulseButton(RequestModel requestModel) {
        GpioPinDigitalOutput pin = optocouperModel.getPinByButton(requestModel.getButton());

        pulseByPin(requestModel, pin, OptocouperModel.SHORT_PULSE);
    }

    public void pulseMiddle(RequestModel requestModel) {
        GpioPinDigitalOutput stopPin = optocouperModel.getPinStop();

        pulseByPin(requestModel, stopPin, OptocouperModel.LONG_PULSE);
    }

    private void pulseByPin(RequestModel requestModel, GpioPinDigitalOutput pin, long pulseTime) {
        optocouperModel.getArrayChannels(requestModel.getChannel()).forEach((channel) -> {
            System.out.println("[PULSE] channel: " + channel + ", button: " + requestModel.getButton());
            selectChannel(channel);
            pin.pulse(pulseTime);
        });

        selectDefaultChannel();
    }

    private void selectChannel(int channel) {
        while (optocouperModel.getCurrentChannel() != channel) {
            increaseChannel();
        }
    }

    private void selectDefaultChannel() {
        while (optocouperModel.getCurrentChannel() != OptocouperModel.MIN_CHANNEL) {
            increaseChannel();
        }
    }

    private void increaseChannel() {
        GpioPinDigitalOutput changePin = optocouperModel.getPinChangeChannel();
        changePin.pulse(OptocouperModel.SHORT_PULSE);
        optocouperModel.increaseChannel();
    }
}
