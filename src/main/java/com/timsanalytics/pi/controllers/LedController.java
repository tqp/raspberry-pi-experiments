package com.timsanalytics.pi.controllers;

import com.pi4j.io.gpio.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/led")
@Tag(name = "LED", description = "LED")
public class LedController {
    private static GpioPinDigitalOutput pin;

    @ResponseBody
    @RequestMapping(value = "/turn-on-then-off", method = RequestMethod.GET)
    @Operation(summary = "Turn On Then Off", description = "Turn On Then Off", tags = {"LED"})
    public String light() {
        if (pin == null) {
            GpioController gpio = GpioFactory.getInstance();

            // Raspberry Pi 4 uses a different pin scheme than prior versions.
            // Ref: https://stackoverflow.com/questions/64584365/raspberry-pi4-with-pi4j-java
            // Fix that here...
            GpioFactory.setDefaultProvider(new RaspiGpioProvider(RaspiPinNumberingScheme.BROADCOM_PIN_NUMBERING));

            pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_19, "MyLed", PinState.LOW);
        }
        pin.toggle();
        return "OK";
    }
}
