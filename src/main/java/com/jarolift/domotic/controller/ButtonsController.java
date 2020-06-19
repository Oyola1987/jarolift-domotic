package com.jarolift.domotic.controller;

import com.jarolift.domotic.model.ErrorResponseModel;
import com.jarolift.domotic.model.RequestModel;
import com.jarolift.domotic.model.ResponseModel;
import com.jarolift.domotic.service.ActionQueuer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ButtonsController {
    private ActionQueuer actionQueuer;
    private static Logger logger = LogManager.getLogger(ButtonsController.class);

    @Autowired
    public ButtonsController(ActionQueuer actionQueuer) {
        this.actionQueuer = actionQueuer;
    }

    @GetMapping(value = "/api/button/{button}/channel/{channel}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity pushButton(@RequestHeader(value = "User-Agent") String userAgent, @PathVariable String button, @PathVariable int channel) {
        return response(new RequestModel(button, channel, userAgent));
    }

    @GetMapping(value = "/api/middle/channel/{channel}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity downMiddleButton(@RequestHeader(value = "User-Agent") String userAgent, @PathVariable int channel) {
        return response(new RequestModel(channel, userAgent));
    }

    private ResponseEntity response(RequestModel request) {
        ResponseEntity responseEntity;

        try {
            actionQueuer.executeRequest(request);
            responseEntity = ResponseEntity.ok(new ResponseModel(request.getButton(), request.getChannel()));
        } catch (IOException e) {
            logger.error("Error user agent: " + request.getUserAgent());
            logger.error("Error message: " + e.getMessage());
            ErrorResponseModel responseModel = new ErrorResponseModel(e.getMessage(), request.getButton(), request.getChannel());
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseModel);
        }

        return responseEntity;
    }
}