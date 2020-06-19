package com.jarolift.domotic.model;

import com.jarolift.domotic.service.PulseDuration;

import java.util.List;

public class ExecuteModel {
    private Pulsable pin;
    private List<Integer> channels;
    private PulseDuration time;
    RequestModel requestModel;

    public ExecuteModel(Pulsable pin, List<Integer> channels, PulseDuration time, RequestModel requestModel) {
        this.pin = pin;
        this.channels = channels;
        this.time = time;
        this.requestModel = requestModel;
    }

    public Pulsable getPin() {
        return pin;
    }

    public List<Integer> getChannels() {
        return channels;
    }

    public PulseDuration getTime() {
        return time;
    }

    public RequestModel getRequestModel() {
        return requestModel;
    }
}
