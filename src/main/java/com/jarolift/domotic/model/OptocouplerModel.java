package com.jarolift.domotic.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class OptocouplerModel {
    public final static int MIN_CHANNEL = 1;
    private final static int MAX_CHANNEL = 8;
    private final static int AVAILABLE_CHANNELS = 2;

    private int currentChannel = MIN_CHANNEL;
    private List<Integer> allChannels = new ArrayList<>();
    private Pulsable pinUp;
    private Pulsable pinDown;
    private Pulsable pinStop;
    private Pulsable pinChangeChannel;

    @Autowired
    public OptocouplerModel(PulsableFactory pulsableFactory) {
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

        throw new IOException("Button '" + button + "' not valid");
    }

    public List<Integer> getArrayChannels(int channel) throws IOException {
        if (channel == 0) {
            return allChannels;
        } else if (allChannels.contains(channel)) {
            return Arrays.asList(channel);
        }

        throw new IOException("Channel '" + channel + "' not valid");
    }

    public void increaseChannel() {
        currentChannel++;
        if (currentChannel > MAX_CHANNEL) {
            currentChannel = MIN_CHANNEL;
        }
    }
}
