package com.jarolift.domotic.controller;

import com.jarolift.domotic.model.RequestModel;
import com.jarolift.domotic.service.SendCommand;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class ButtonsController {
    @GetMapping(value = "/api/event/{event}/button/{button}/channel/{channel}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RequestModel eventButton(@PathVariable String event, @PathVariable String button, @PathVariable int channel) {
        RequestModel request = new RequestModel(event, button, channel);
        return execute(request);
    }

    @GetMapping(value = "/api/middle/channel/{channel}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RequestModel middleButton(@PathVariable int channel) {
        RequestModel request = new RequestModel(channel);
        return execute(request);
    }

    private RequestModel execute(RequestModel requestModel) {
        SendCommand sendCommand = new SendCommand();
        sendCommand.update(requestModel);
        return requestModel;
    }
}