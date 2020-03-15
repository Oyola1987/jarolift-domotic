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

    @GetMapping(value = "/api/event/down/button/{button}/channel/{channel}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RequestModel pushButton(@PathVariable String button, @PathVariable int channel) {
        RequestModel request = new RequestModel(button, channel);
        optocouperService.pushed(request);
        return request;
    }

    @GetMapping(value = "/api/event/up/button/{button}/channel/{channel}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RequestModel unPushButton(@PathVariable String button, @PathVariable int channel) {
        RequestModel request = new RequestModel(button, channel);
        optocouperService.unPushed(request);
        return request;
    }

    @GetMapping(value = "/api/event/down/middle/channel/{channel}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RequestModel downMiddleButton(@PathVariable int channel) {
        RequestModel request = new RequestModel(channel);
        optocouperService.pushedMiddle(request);
        return request;
    }

    @GetMapping(value = "/api/event/up/middle/channel/{channel}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String upMiddleButton() {
        return "{}";
    }
}