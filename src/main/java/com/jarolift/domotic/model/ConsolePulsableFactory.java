package com.jarolift.domotic.model;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@Profile("local")
public class ConsolePulsableFactory implements PulsableFactory {
    @Override
    public Pulsable getPinDown() {
        return log("DOWN");
    }

    @Override
    public Pulsable getPinStop() {
        return log("STOP");
    }

    @Override
    public Pulsable getPinUp() {
        return log("UP");
    }

    @Override
    public Pulsable getPinChangeChannel() {
        return log("CHANGE CHANNEL");
    }

    private Pulsable log(String button) {
        return (duration) -> {
            System.out.println("button: " + button + " pulsed, " + duration);
            return CompletableFuture.completedFuture(null);
        };
    }
}
