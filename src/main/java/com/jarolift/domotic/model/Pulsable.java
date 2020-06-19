package com.jarolift.domotic.model;

import com.jarolift.domotic.service.PulseDuration;

public interface Pulsable {
    void pulse(PulseDuration duration);
}
