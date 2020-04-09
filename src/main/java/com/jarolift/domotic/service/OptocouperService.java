package com.jarolift.domotic.service;

import com.jarolift.domotic.model.OptocouperModel;
import com.jarolift.domotic.model.Pulsable;
import com.jarolift.domotic.model.PulsableFactory;
import com.jarolift.domotic.model.RequestModel;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class OptocouperService {
    private final static long SHORT_PULSE = 500;
    private final static long LONG_PULSE = 4000;

    private OptocouperModel optocouperModel;
    private Logger logger;

    @Autowired
    public OptocouperService(PulsableFactory pulsableFactory) {
        optocouperModel = new OptocouperModel(pulsableFactory);
        logger = LogManager.getLogger(OptocouperService.class);
    }

    public void execute(RequestModel requestModel) throws IOException {
        if (requestModel.isMiddleButton()) {
            Pulsable stopPin = optocouperModel.getPinStop();
            pulseByPin(requestModel, stopPin, LONG_PULSE);
        } else {
            Pulsable pin = optocouperModel.getPinByButton(requestModel.getButton());
            pulseByPin(requestModel, pin, SHORT_PULSE);
        }
    }

    private void pulseByPin(RequestModel requestModel, Pulsable pin, long pulseTime) throws IOException {
        List<Integer> channels = optocouperModel.getArrayChannels(requestModel.getChannel());

        for (int channel: channels) {
            logger.info("[PULSE] channel: " + channel + ", button: " + requestModel.getButton());
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
        changePin.pulse(SHORT_PULSE);
        optocouperModel.increaseChannel();
    }
}
