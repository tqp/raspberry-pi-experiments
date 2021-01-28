package com.timsanalytics.pi.controllers;

import com.pi4j.io.gpio.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LedController {
    private static GpioPinDigitalOutput pin;

    @RequestMapping("/")
    public String greeting() {
        return "Hello World!";
    }

    @RequestMapping("/light")
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
