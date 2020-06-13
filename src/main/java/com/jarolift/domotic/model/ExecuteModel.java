package com.jarolift.domotic.model;

import java.util.List;

public class ExecuteModel {
    private Pulsable pin;
    private List<Integer> channels;
    private long time;
    RequestModel requestModel;

    public ExecuteModel(Pulsable pin, List<Integer> channels, long time, RequestModel requestModel) {
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

    public long getTime() {
        return time;
    }

    public RequestModel getRequestModel() {
        return requestModel;
    }
}
