package com.timsanalytics.pi.controllers;

import com.pi4j.io.gpio.GpioPinDigitalOutput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dht")
@Tag(name = "DHT-11", description = "DHT-11")
public class Dht11Controller {
    private static GpioPinDigitalOutput pin;

    @ResponseBody
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @Operation(summary = "Test", description = "Test", tags = {"DHT-11"})
    public void test() {
        System.out.println("DHT-11");
    }
}
