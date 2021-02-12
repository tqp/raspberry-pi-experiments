package com.timsanalytics.pi.controllers;

import com.timsanalytics.pi.services.MotionSensorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/motion-sensor")
@Tag(name = "Motion Sensor", description = "Motion Sensor")
public class MotionSensorController {
    private final MotionSensorService motionSensorService;

    @Autowired
    public MotionSensorController(MotionSensorService motionSensorService) {
        this.motionSensorService = motionSensorService;
    }

    @ResponseBody
    @RequestMapping(value = "/start", method = RequestMethod.GET)
    @Operation(summary = "Start", description = "Start", tags = {"Motion Sensor"})
    public void startTwo() {
        System.out.println("Starting Motion Sensor");
        this.motionSensorService.start();
    }

}
