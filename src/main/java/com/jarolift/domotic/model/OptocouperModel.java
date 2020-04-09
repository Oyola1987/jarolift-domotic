package com.jarolift.domotic.model;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OptocouperModel {
    public final static int MIN_CHANNEL = 1;
    private final static int MAX_CHANNEL = 8;
    private final static int AVAILABLE_CHANNELS = 2;

    private int currentChannel = MIN_CHANNEL;
    private List<Integer> allChannels = new ArrayList<>();
    private Pulsable pinUp;
    private Pulsable pinDown;
    private Pulsable pinStop;
    private Pulsable pinChangeChannel;
    private Logger logger;

    public OptocouperModel(PulsableFactory pulsableFactory) {
        logger = LogManager.getLogger(OptocouperModel.class);
        pinDown = pulsableFactory.getPinDown();
        pinStop = pulsableFactory.getPinStop();
        pinUp = pulsableFactory.getPinUp();
        pinChangeChannel = pulsableFactory.getPinChangeChannel();

        for (int i = MIN_CHANNEL; i <= AVAILABLE_CHANNELS; i++) {
            allChannels.add(i);
        }
    }

    public Pulsable getPinChangeChannel() {
        return pinChangeChannel;
    }

    public Pulsable getPinStop() {
        return pinStop;
    }

    public int getCurrentChannel() {
        // logger.info("[CURRENT CHANNEL] " + currentChannel);
        return currentChannel;
    }

    public Pulsable getPinByButton(String button) throws IOException {
        if (button.equals("up")){
            return pinUp;
        } else if (button.equals("down")){
            return pinDown;
        } else if (button.equals("stop")) {
            return pinStop;
        }

        throw new IOException(button + "' button not valid");
    }

    public List<Integer> getArrayChannels(int channel) throws IOException {
        if (channel == 0) {
            return allChannels;
        } else if (allChannels.contains(channel)) {
            List<Integer> channels = new ArrayList<>();
            channels.add(channel);
            return channels;
        }

        throw new IOException("Error channel '" + channel + "' not valid");
    }

    public void increaseChannel() {
        currentChannel++;
        if (currentChannel == (MAX_CHANNEL + 1)) {
            currentChannel = MIN_CHANNEL;
        }
    }
}
