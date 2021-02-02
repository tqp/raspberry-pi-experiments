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
    @RequestMapping(value = "/toggle", method = RequestMethod.GET)
    @Operation(summary = "Toggle LED", description = "Toggle LED", tags = {"LED"})
    public void light() {
        if (pin == null) {
            GpioController gpio = GpioFactory.getInstance();
            GpioFactory.setDefaultProvider(new RaspiGpioProvider(RaspiPinNumberingScheme.BROADCOM_PIN_NUMBERING));
            pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_19, "MyLed", PinState.LOW);
        }
        pin.toggle();
        System.out.println("Current state is " + pin.getState());
    }

    @ResponseBody
    @RequestMapping(value = "/on", method = RequestMethod.GET)
    @Operation(summary = "LED Off", description = "LED Off", tags = {"LED"})
    public void turnOn() {
        if (pin == null) {
            GpioController gpio = GpioFactory.getInstance();
            GpioFactory.setDefaultProvider(new RaspiGpioProvider(RaspiPinNumberingScheme.BROADCOM_PIN_NUMBERING));
            pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_19, "MyLed", PinState.LOW);
        }
        pin.high();
    }

    @ResponseBody
    @RequestMapping(value = "/off", method = RequestMethod.GET)
    @Operation(summary = "LED On", description = "LED On", tags = {"LED"})
    public void turnOff() {
        if (pin == null) {
            GpioController gpio = GpioFactory.getInstance();
            GpioFactory.setDefaultProvider(new RaspiGpioProvider(RaspiPinNumberingScheme.BROADCOM_PIN_NUMBERING));
            pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_19, "MyLed", PinState.LOW);
        }
        pin.low();
    }
}
