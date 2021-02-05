package com.timsanalytics.pi.controllers;

import com.timsanalytics.pi.services.LedService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/led")
@Tag(name = "LED", description = "LED")
public class LedController {
    private final LedService ledService;

    @Autowired
    public LedController(LedService ledService) {
        this.ledService = ledService;
    }

    @ResponseBody
    @RequestMapping(value = "/toggle", method = RequestMethod.GET)
    @Operation(summary = "Toggle LED", description = "Toggle LED", tags = {"LED"})
    public void toggleLight() {
        this.ledService.toggleLight();
    }

    @ResponseBody
    @RequestMapping(value = "/on", method = RequestMethod.GET)
    @Operation(summary = "LED Off", description = "LED Off", tags = {"LED"})
    public void turnOn() {
        this.ledService.turnOn();
    }

    @ResponseBody
    @RequestMapping(value = "/off", method = RequestMethod.GET)
    @Operation(summary = "LED On", description = "LED On", tags = {"LED"})
    public void turnOff() {
        this.ledService.turnOff();
    }
}
