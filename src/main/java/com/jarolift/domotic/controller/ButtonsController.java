package com.jarolift.domotic.controller;

import com.jarolift.domotic.model.RequestModel;
import com.jarolift.domotic.service.OptocouperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ButtonsController {
    private OptocouperService optocouperService;
    private boolean executing = false;

    @Autowired
    public ButtonsController(OptocouperService optocouperService) {
        this.optocouperService = optocouperService;
    }

    @GetMapping(value = "/api/button/{button}/channel/{channel}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity pushButton(@PathVariable String button, @PathVariable int channel) {
        return response(new RequestModel(button, channel));
    }

    @GetMapping(value = "/api/middle/channel/{channel}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity downMiddleButton(@PathVariable int channel) {
        return response(new RequestModel(channel));
    }

    private ResponseEntity response(RequestModel request) {
        if (executing) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
        } else {
            executing = true;
            optocouperService.execute(request);
            executing = false;
            return ResponseEntity.ok(request);
        }
    }
}