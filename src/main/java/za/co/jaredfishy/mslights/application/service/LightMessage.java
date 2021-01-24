package za.co.jaredfishy.mslights.application.service;

import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public class LightMessage implements Callable<String>{


    @Override
    public String call() throws Exception {
        Thread.sleep(1000);
        return "Yay";
    }
}
