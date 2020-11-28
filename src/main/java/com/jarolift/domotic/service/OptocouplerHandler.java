package com.jarolift.domotic.service;

import com.jarolift.domotic.model.OptocouplerModel;
import com.jarolift.domotic.model.Pulsable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OptocouplerHandler {
    private OptocouplerModel optocouplerModel;

    @Autowired
    public OptocouplerHandler(OptocouplerModel optocouplerModel) {
        this.optocouplerModel = optocouplerModel;
    }

    public void selectDefaultChannel() {
        selectChannel(OptocouplerModel.MIN_CHANNEL);
    }

    public void selectChannel(int channel) {
        while (optocouplerModel.getCurrentChannel() != channel) {
            increaseChannel();
        }
    }

    private void increaseChannel() {
        Pulsable changePin = optocouplerModel.getPinChangeChannel();
        changePin.pulse(PulseDuration.CHANGE_CHANNEL, PulseDuration.CHANGE_CHANNEL);
        optocouplerModel.increaseChannel();
    }
}
