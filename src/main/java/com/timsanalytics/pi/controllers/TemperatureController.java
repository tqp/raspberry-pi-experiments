package com.timsanalytics.pi.controllers;

import com.timsanalytics.pi.services.TemperatureService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temperature")
@Tag(name = "Temperature", description = "Temperature")
public class TemperatureController {
    private final TemperatureService temperatureService;

    @Autowired
    public TemperatureController(TemperatureService temperatureService) {
        this.temperatureService = temperatureService;
    }

    @ResponseBody
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @Operation(summary = "Test", description = "Test", tags = {"Temperature"})
    public void test() {
        this.temperatureService.test();
    }
}
