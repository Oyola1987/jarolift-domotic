package com.jarolift.domotic.model;

import java.util.concurrent.Future;

public interface Pulsable {
    Future<?> pulse(long time);
}
