package com.jarolift.domotic.model;

import com.jarolift.domotic.service.PulseDuration;

import java.util.List;

public class ExecuteModel {
    private Pulsable pin;
    private List<Integer> channels;
    private PulseDuration durationDown;
    private PulseDuration durationUp;
    RequestModel requestModel;

    public ExecuteModel(Pulsable pin, List<Integer> channels, PulseDuration durationDown, PulseDuration durationUp, RequestModel requestModel) {
        this.pin = pin;
        this.channels = channels;
        this.durationDown = durationDown;
        this.durationUp = durationUp;
        this.requestModel = requestModel;
    }

    public Pulsable getPin() {
        return pin;
    }

    public List<Integer> getChannels() {
        return channels;
    }

    public PulseDuration getDurationDown() {
        return durationDown;
    }

    public PulseDuration getDurationUp() {
        return durationUp;
    }

    public RequestModel getRequestModel() {
        return requestModel;
    }
}
