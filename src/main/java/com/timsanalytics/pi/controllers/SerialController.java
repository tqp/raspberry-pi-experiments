package com.timsanalytics.pi.controllers;

import com.timsanalytics.pi.services.SerialService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/serial")
@Tag(name = "Serial Port", description = "Serial Port")
public class SerialController {
    private final SerialService serialService;

    @Autowired
    public SerialController(SerialService serialService) {
        this.serialService = serialService;
    }

    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @Operation(summary = "Test Serial Port", description = "Test Serial Port", tags = {"Serial Port"})
    public void toggleLight() {
        this.serialService.testSerialPort();
    }
}
