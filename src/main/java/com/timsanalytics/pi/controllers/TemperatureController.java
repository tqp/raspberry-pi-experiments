package com.timsanalytics.pi.controllers;

import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.timsanalytics.pi.services.TemperatureService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dht")
@Tag(name = "DHT-11", description = "DHT-11")
public class TemperatureController {
    private static GpioPinDigitalOutput pin;
    private final TemperatureService temperatureService;

    @Autowired
    public TemperatureController(TemperatureService temperatureService) {
        this.temperatureService = temperatureService;
    }

    @ResponseBody
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @Operation(summary = "Test", description = "Test", tags = {"DHT-11"})
    public void Test() {
        System.out.println("DHT-11");
    }

    @ResponseBody
    @RequestMapping(value = "/get-temp", method = RequestMethod.GET)
    @Operation(summary = "Get Temperature", description = "Get Temperature", tags = {"DHT-11"})
    public void GetTemperature() throws Exception {
        for (int i = 0; i < 10; i++) {
            Thread.sleep(2000);
            temperatureService.getTemperature(21);
        }
        System.out.println("Done!!");
    }
}
