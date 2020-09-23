package com.jarolift.domotic.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BlindsModel {
    private List<BlindState> blinds = new ArrayList<>();

    public BlindsModel() {
        blinds.add(new BlindState("living-room", 1, 100, 10.0f));
        blinds.add(new BlindState("kitchen", 2, 100, 10.0f));
    }

    public Boolean validateChannel(int channel) {
        return channel > 0 && channel <= blinds.size();
    }

    public List<Integer> getAllChannels() {
        List<Integer> channels = new ArrayList<>();

        blinds.forEach(blind -> {
            channels.add(blind.getChannel());
        });

        return channels;
    }
}
