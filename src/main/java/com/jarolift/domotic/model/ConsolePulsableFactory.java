package com.jarolift.domotic.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("local")
public class ConsolePulsableFactory implements PulsableFactory {
    private Logger logger = LogManager.getLogger(ConsolePulsableFactory.class);

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
        return (durationDown, durationUp) -> {
            try {
                logger.warn("[DEV] [BUTTON] '" + button + "' pulsed, duringDown: " + durationDown.duration + ", duringUp: " + durationDown.duration);
                Thread.sleep(durationDown.duration);
                Thread.sleep(durationUp.duration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
    }
}
