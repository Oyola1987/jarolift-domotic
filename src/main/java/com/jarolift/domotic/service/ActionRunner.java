package com.jarolift.domotic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedQueue;

@Component
public class ActionRunner {
    private Queue<Action> actions;
    private Timer timer;
    private StopWatch cron;
    private OptocouplerHandler optocouplerHandler;
    private Integer timeToDefaultChannel;

    @Autowired
    public ActionRunner(OptocouplerHandler optocouplerHandler, @Value("${jarolift-domotic.time-to-default-channel}") Integer timeToDefaultChannel) {
        this.timeToDefaultChannel = timeToDefaultChannel;
        this.optocouplerHandler = optocouplerHandler;
        cron = new StopWatch();
        actions = new ConcurrentLinkedQueue<>();
        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                executeFirst();
            };
        };
        timer.schedule(task, 0, 100L);
    }

    public void add(Action action) {
        actions.add(action);
    }

    private void executeFirst() {
        if(cron.isRunning()) {
            cron.stop();
            if(cron.getTotalTimeSeconds() > timeToDefaultChannel) {
                optocouplerHandler.selectDefaultChannel();
            } else {
                cron.start();
            }
        }

        Action action = actions.poll();
        if(action == null) return;
        action.execute();
        cron = new StopWatch();
        cron.start();
    }
}
