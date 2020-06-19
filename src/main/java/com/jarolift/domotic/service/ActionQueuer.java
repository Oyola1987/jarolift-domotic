package com.jarolift.domotic.service;

import com.jarolift.domotic.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class ActionQueuer {
    private OptocouplerModel optocouplerModel;
    private OptocouplerHandler optocouplerHandler;
    private ActionRunner actionRunner;

    @Autowired
    public ActionQueuer(OptocouplerModel optocouplerModel, OptocouplerHandler optocouplerHandler, ActionRunner actionRunner) {
        this.optocouplerModel = optocouplerModel;
        this.optocouplerHandler = optocouplerHandler;
        this.actionRunner = actionRunner;
    }

    public void executeRequest(RequestModel requestModel) throws IOException {
        List<Integer> channels = optocouplerModel.getArrayChannels(requestModel.getChannel());
        Pulsable pin = requestModel.isMiddleButton() ? optocouplerModel.getPinStop() : optocouplerModel.getPinByButton(requestModel.getButton());
        PulseDuration time = requestModel.isMiddleButton() ? PulseDuration.LONG_PULSE : PulseDuration.SHORT_PULSE;
        ExecuteModel executeModel = new ExecuteModel(pin, channels, time, requestModel);

        Action executePulse = new ExecutePulse(executeModel, optocouplerHandler);
        actionRunner.add(executePulse);
    }
}
