package com.timsanalytics.pi.controllers;

import com.pi4j.io.gpio.GpioPinDigitalOutput;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dht")
@Tag(name = "DHT-11", description = "DHT-11")
public class Dht11Controller {
    private static GpioPinDigitalOutput pin;

    @RequestMapping("/test")
    public void test() {
        System.out.println("DHT");
    }
}
