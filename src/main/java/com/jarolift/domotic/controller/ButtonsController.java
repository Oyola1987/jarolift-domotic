package com.jarolift.domotic.controller;

import com.jarolift.domotic.model.RequestModel;
import com.jarolift.domotic.service.OptocouperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ButtonsController {
    private OptocouperService optocouperService;

    @Autowired
    public ButtonsController(OptocouperService optocouperService) {
        this.optocouperService = optocouperService;
    }

    @GetMapping(value = "/api/button/{button}/channel/{channel}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RequestModel pushButton(@PathVariable String button, @PathVariable int channel) {
        RequestModel request = new RequestModel(button, channel);
        optocouperService.pulseButton(request);
        return request;
    }

    @GetMapping(value = "/api/middle/channel/{channel}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RequestModel downMiddleButton(@PathVariable int channel) {
        RequestModel request = new RequestModel("middle", channel);
        optocouperService.pulseMiddle(request);
        return request;
    }
}