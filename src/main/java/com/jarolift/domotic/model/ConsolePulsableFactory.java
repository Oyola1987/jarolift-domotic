package com.jarolift.domotic.model;

import com.jarolift.domotic.service.PulseDuration;
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
        return (duration) -> {
            try {
                logger.warn("[DEV] [BUTTON] '" + button + "' pulsed, during: " + duration.duration);
                Thread.sleep(duration.duration);
                Thread.sleep(PulseDuration.SHORT_PULSE.duration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
    }
}
