package com.jarolift.domotic.service;

import com.jarolift.domotic.model.OptocouperModel;
import com.jarolift.domotic.model.Pulsable;
import com.jarolift.domotic.model.PulsableFactory;
import com.jarolift.domotic.model.RequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OptocouperService {
    private OptocouperModel optocouperModel;

    @Autowired
    public OptocouperService(PulsableFactory pulsableFactory) {
        optocouperModel = new OptocouperModel(pulsableFactory);
    }

    public void execute(RequestModel requestModel) {
        if (requestModel.isMiddleButton()) {
            Pulsable stopPin = optocouperModel.getPinStop();
            pulseByPin(requestModel, stopPin, OptocouperModel.LONG_PULSE);
        } else {
            Pulsable pin = optocouperModel.getPinByButton(requestModel.getButton());
            pulseByPin(requestModel, pin, OptocouperModel.SHORT_PULSE);
        }
    }

    private void pulseByPin(RequestModel requestModel, Pulsable pin, long pulseTime) {
        List<Integer> channels = optocouperModel.getArrayChannels(requestModel.getChannel());

        for (int channel: channels) {
            System.out.println("[PULSE] channel: " + channel + ", button: " + requestModel.getButton());
            selectChannel(channel);
            pin.pulse(pulseTime);
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
        changePin.pulse(OptocouperModel.SHORT_PULSE);
        optocouperModel.increaseChannel();
    }
}
