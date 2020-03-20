package com.jarolift.domotic.service;

import com.jarolift.domotic.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Component
public class OptocouperService {
    private OptocouperModel optocouperModel;

    @Autowired
    public OptocouperService(PulsableFactory pulsableFactory) {
        optocouperModel = new OptocouperModel(pulsableFactory);
    }

    public void pulseButton(RequestModel requestModel) {
        Pulsable pin = optocouperModel.getPinByButton(requestModel.getButton());

        pulseByPin(requestModel, pin, OptocouperModel.SHORT_PULSE);
    }

    public void pulseMiddle(RequestModel requestModel) {
        Pulsable stopPin = optocouperModel.getPinStop();

        pulseByPin(requestModel, stopPin, OptocouperModel.LONG_PULSE);
    }

    private void pulseByPin(RequestModel requestModel, Pulsable pin, long pulseTime) {
        List<Integer> channels = optocouperModel.getArrayChannels(requestModel.getChannel());

        for (int channel: channels) {
            System.out.println("[PULSE] channel: " + channel + ", button: " + requestModel.getButton());
            selectChannel(channel);
            pulse(pin, pulseTime);
        }

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
        Pulsable changePin = optocouperModel.getPinChangeChannel();
        pulse(changePin, OptocouperModel.SHORT_PULSE);
        optocouperModel.increaseChannel();
    }

    private void pulse(Pulsable pin, long pulseTime) {
        try {
            pin.pulse(pulseTime).get();
            Thread.sleep(OptocouperModel.STOP_PULSE);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
