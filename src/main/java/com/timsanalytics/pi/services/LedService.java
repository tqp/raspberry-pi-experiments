package com.timsanalytics.pi.services;

import com.pi4j.io.gpio.*;
import org.springframework.stereotype.Service;

@Service
public class LedService {
    private static GpioPinDigitalOutput pin;

    public void toggleLight() {
        if (pin == null) {
            GpioController gpio = GpioFactory.getInstance();
            GpioFactory.setDefaultProvider(new RaspiGpioProvider(RaspiPinNumberingScheme.BROADCOM_PIN_NUMBERING));
            pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_19, "MyLed", PinState.LOW);
        }
        pin.toggle();
        System.out.println("Current state is " + pin.getState());
    }

    public void turnOn() {
        if (pin == null) {
            GpioController gpio = GpioFactory.getInstance();
            GpioFactory.setDefaultProvider(new RaspiGpioProvider(RaspiPinNumberingScheme.BROADCOM_PIN_NUMBERING));
            pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_19, "MyLed", PinState.LOW);
        }
        pin.high();
    }

    public void turnOff() {
        if (pin == null) {
            GpioController gpio = GpioFactory.getInstance();
            GpioFactory.setDefaultProvider(new RaspiGpioProvider(RaspiPinNumberingScheme.BROADCOM_PIN_NUMBERING));
            pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_19, "MyLed", PinState.LOW);
        }
        pin.low();
    }
}
