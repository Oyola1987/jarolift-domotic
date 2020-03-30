package com.jarolift.domotic.model;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("local")
public class ConsolePulsableFactory implements PulsableFactory {
    @Override
    public Pulsable getPinDown() {
        return log("down");
    }

    @Override
    public Pulsable getPinStop() {
        return log("stop");
    }

    @Override
    public Pulsable getPinUp() {
        return log("up");
    }

    @Override
    public Pulsable getPinChangeChannel() {
        return log("change channel");
    }

    private Pulsable log(String button) {
        return (duration) -> {
            try {
                System.out.println("[DEV] [BUTTON] '" + button + "' pulsed, during: " + duration);
                Thread.sleep(duration);
                Thread.sleep(GpioPulsable.WAIT_TO_PULSE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
    }
}
