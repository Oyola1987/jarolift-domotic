package com.jarolift.domotic.controller;

import com.jarolift.domotic.model.RequestModel;
import com.jarolift.domotic.service.SendCommand;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ButtonsController {
    @RequestMapping(value = "/api/event/{event}/button/{button}/channel/{channel}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public RequestModel eventButton(
            @PathVariable("event") String event,
            @PathVariable("button") String button,
            @PathVariable("channel") int channel
    ) {
        RequestModel request = new RequestModel(event, button, channel);
        return execute(request);
    }

    @RequestMapping(value = "/api/button/{button}/channel/{channel}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public RequestModel clickButton(@PathVariable("button") String button, @PathVariable("channel") int channel) {
        RequestModel request = new RequestModel(button, channel);
        return execute(request);
    }

    private RequestModel execute(RequestModel requestModel) {
        SendCommand sendCommand = new SendCommand();
        sendCommand.update(requestModel);
        return requestModel;
    }
}