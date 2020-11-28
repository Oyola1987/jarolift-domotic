package com.jarolift.domotic.service;

import com.jarolift.domotic.model.ExecuteModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

class ExecutePulse implements Action {
    private OptocouplerHandler optocouplerHandler;
    private ExecuteModel executeModel;
    private static Logger logger = LogManager.getLogger(ExecutePulse.class);

    public ExecutePulse(ExecuteModel executeModel, OptocouplerHandler optocouplerHandler) {
        this.optocouplerHandler = optocouplerHandler;
        this.executeModel = executeModel;
    }

    @Override
    public void execute() {
        List<Integer> channels = executeModel.getChannels();

        for (int channel: channels) {
            logger.info("[USER AGENT]: " + executeModel.getRequestModel().getUserAgent());
            logger.info("[PULSE] channel: " + channel + ", button: " + executeModel.getRequestModel().getButton());
            optocouplerHandler.selectChannel(channel);
            executeModel.getPin().pulse(executeModel.getDurationDown(), executeModel.getDurationUp());
        }
    }
}
