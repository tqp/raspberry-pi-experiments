package com.timsanalytics.pi.services;

import com.pi4j.io.gpio.GpioPinDigitalOutput;
import org.springframework.stereotype.Service;

@Service
public class TemperatureService {
    private static GpioPinDigitalOutput pin;

    public void test() {
        System.out.println("Test");
    }
}
