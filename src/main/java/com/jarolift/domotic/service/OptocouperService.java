package com.jarolift.domotic.service;

import com.jarolift.domotic.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CompletableFuture;

@Component
public class OptocouperService {
    private final static long SHORT_PULSE = 500;
    private final static long LONG_PULSE = 4000;

    private Logger logger;
    private StorageChannel storageChannel;
    private OptocouperModel optocouperModel;
    private LinkedList<ExecuteModel> pendingRequests = new LinkedList<>();
    private boolean isExecuting = false;
    Timer intervalTimer = null;

    @Autowired
    public OptocouperService(PulsableFactory pulsableFactory) {
        logger = LogManager.getLogger(OptocouperService.class);
        storageChannel = new StorageChannel();
        optocouperModel = new OptocouperModel(pulsableFactory);
        optocouperModel.setCurrentChannelFromString(storageChannel.readChannel());
    }

    public void executeRequest(RequestModel requestModel) throws IOException {
        List<Integer> channels = optocouperModel.getArrayChannels(requestModel.getChannel());
        Pulsable pin = requestModel.isMiddleButton() ? optocouperModel.getPinStop() : optocouperModel.getPinByButton(requestModel.getButton());
        long time = requestModel.isMiddleButton() ? LONG_PULSE : SHORT_PULSE;
        ExecuteModel executeModel = new ExecuteModel(pin, channels, time, requestModel);

        if (isExecuting) {
            pendingRequests.add(executeModel);
        } else {
            isExecuting = true;
            CompletableFuture.runAsync(() -> {
                executeSync(executeModel);
                isExecuting = false;
            });
        }
    }

    private void setDefaultChannelDelayed() {
        intervalTimer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                isExecuting = true;
                intervalTimer.cancel();
                selectChannel(OptocouperModel.MIN_CHANNEL);
                executePendings();
                isExecuting = false;
            };
        };
        intervalTimer.schedule(task, 60000L );
    }

    private void executeSync(ExecuteModel executeModel) {
        if(intervalTimer != null) {
            intervalTimer.cancel();
            intervalTimer = null;
        }

        pulseByPin(executeModel);

        if (pendingRequests.size() == 0) {
            setDefaultChannelDelayed();
        }

        executePendings();
    }

    private void executePendings() {
        if (pendingRequests.size() > 0) {
            executeSync(pendingRequests.pollFirst());
        }
    }

    private void pulseByPin(ExecuteModel executeModel) {
        List<Integer> channels = executeModel.getChannels();

        for (int channel: channels) {
            logger.info("[USER AGENT]: " + executeModel.getRequestModel().getUserAgent());
            logger.info("[PULSE] channel: " + channel + ", button: " + executeModel.getRequestModel().getButton());
            selectChannel(channel);
            executeModel.getPin().pulse(executeModel.getTime());
        }
    }

    private void selectChannel(int channel) {
        while (optocouperModel.getCurrentChannel() != channel) {
            increaseChannel();
        }
    }

    private void increaseChannel() {
        Pulsable changePin = optocouperModel.getPinChangeChannel();
        changePin.pulse(SHORT_PULSE);
        optocouperModel.increaseChannel();
        storageChannel.writeChannel(optocouperModel.getCurrentChannel());
    }
}
