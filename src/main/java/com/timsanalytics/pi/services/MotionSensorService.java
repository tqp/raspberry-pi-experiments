package com.timsanalytics.pi.services;

import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import com.pi4j.wiringpi.GpioUtil;
import org.springframework.stereotype.Service;

@Service
public class MotionSensorService {

    public void start() {
        //This is required to enable Non Privileged Access to avoid applying sudo to run Pi4j programs
        GpioUtil.enableNonPrivilegedAccess();

        final GpioController gpioPIRMotionSensor = GpioFactory.getInstance();
        GpioFactory.setDefaultProvider(new RaspiGpioProvider(RaspiPinNumberingScheme.BROADCOM_PIN_NUMBERING));
        final GpioPinDigitalInput pirMotionSensor = gpioPIRMotionSensor.provisionDigitalInputPin(RaspiPin.GPIO_26, PinPullResistance.PULL_DOWN);

        //Create gpio controller for LED listening on the pin GPIO_01 with default PinState as LOW
        final GpioController gpioLED = GpioFactory.getInstance();
        final GpioPinDigitalOutput led = gpioLED.provisionDigitalOutputPin(RaspiPin.GPIO_01, "LED", PinState.LOW);
        led.low();

        //Create and register gpio pin listener on PIRMotion Sensor GPIO Input instance
        pirMotionSensor.addListener(new GpioPinListenerDigital() {

            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                //if the event state is High then print "Intruder Detected" and turn the LED ON by invoking the high() method
                if (event.getState().isHigh()) {
                    System.out.println("Motion Detected!");
                    led.high();
                }
                //if the event state is Low then print "All is quiet.." and make the LED OFF by invoking the low() method
                if (event.getState().isLow()) {
                    System.out.println("All is quiet...");
                    led.low();
                }
            }
        });

        try {
            // keep program running until user aborts
            for (; ; ) {
                //Thread.sleep(500);
            }
        } catch (final Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
