package com.jarolift.domotic.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
public class ButtonsController {
    @RequestMapping(
            value = "/api/event/{event}/button/{button}/channel/{channel}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Object index(
            @PathVariable("event") String event,
            @PathVariable("button") String button,
            @PathVariable("channel") int channel
    ) {
        String response = button.toUpperCase() + " button, event: " + event + ", channel: " + channel;
        return Collections.singletonMap("response", response);
    }
}