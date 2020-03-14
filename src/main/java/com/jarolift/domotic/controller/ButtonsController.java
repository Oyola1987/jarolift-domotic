package com.jarolift.domotic.controller;

import com.jarolift.domotic.model.RequestModel;
import com.jarolift.domotic.service.OptocouperService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ButtonsController {
    private OptocouperService optocouperService = new OptocouperService();

    @GetMapping(value = "/api/event/{event}/button/{button}/channel/{channel}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RequestModel eupMiddleButtonventButton(@PathVariable String event, @PathVariable String button, @PathVariable int channel) {
        RequestModel request = new RequestModel(event, button, channel);
        optocouperService.updateState(request);
        return request;
    }

    @GetMapping(value = "/api/event/down/middle/channel/{channel}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RequestModel downMiddleButton(@PathVariable int channel) {
        RequestModel request = new RequestModel(channel);
        optocouperService.updateMiddle(request);
        return request;
    }

    @GetMapping(value = "/api/event/up/middle/channel/{channel}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String upMiddleButton() {
        return "{}";
    }
}