package com.jarolift.domotic.model;

import java.util.ArrayList;
import java.util.List;

public class OptocouperModel {
    public final static long LONG_PULSE = 4200;
    public final static long SHORT_PULSE = 300;
    public final static int MIN_CHANNEL = 1;
    private final static int MAX_CHANNEL = 8;
    private final static int AVAILABLE_CHANNELS = 2;

    private int currentChannel = MIN_CHANNEL;
    private List<Integer> allChannels = new ArrayList<>();
    private Pulsable pinUp;
    private Pulsable pinDown;
    private Pulsable pinStop;
    private Pulsable pinChangeChannel;

    public OptocouperModel(PulsableFactory pulsableFactory) {
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
        System.out.println("[CURRENT CHANNEL] " + currentChannel);
        return currentChannel;
    }

    public Pulsable getPinByButton(String button) {
        if (button.equals("up")){
            return pinUp;
        } else if (button.equals("down")){
            return pinDown;
        } else if (button.equals("stop")) {
            return pinStop;
        }

        System.out.println("[ERROR] Error '" + button + "' button not valid, using 'STOP' button by default");
        return pinStop;
    }

    public List<Integer> getArrayChannels(int channel) {
        List<Integer> channels = new ArrayList<>();

        if (channel == 0) {
            channels = allChannels;
        } else if (allChannels.contains(channel)) {
            channels.add(channel);
        } else {
            System.out.println("[ERROR] Error channel '" + channel + "' not valid, using channel '1' by default");
            channels.add(MIN_CHANNEL);
        }

        return channels;
    }

    public void increaseChannel() {
        currentChannel++;
        if (currentChannel == (MAX_CHANNEL + 1)) {
            currentChannel = MIN_CHANNEL;
        }
    }
}
