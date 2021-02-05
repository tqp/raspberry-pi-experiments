package com.timsanalytics.pi.controllers;

import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import com.pi4j.wiringpi.GpioUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/motion-sensor")
@Tag(name = "Motion Sensor", description = "Motion Sensor")
public class MotionSensorController {

    @ResponseBody
    @RequestMapping(value = "/start-one", method = RequestMethod.GET)
    @Operation(summary = "Start 1", description = "Start 1", tags = {"Motion Sensor"})
    public void startOne() {
        System.out.println("Starting Motion Sensor 1");

        // Create Gpio Controller
        GpioController gpio = GpioFactory.getInstance();

        // provision the necessary pins in order to use it
        // provision PIR pin as input and LedPin as output
        GpioPinDigitalInput pir = gpio.provisionDigitalInputPin(RaspiPin.GPIO_26);
//        GpioPinDigitalOutput ledPin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04);

        // Ensure the GPIO pins states are not active or leaving some activity engaged
        //if the program is shutdown.
        pir.setShutdownOptions(true);

        // monitor for Pin State changes
        // if state is high, ON Led
        pir.addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                System.out.println(event.getState());

//                // turn led ON if pin attached to PIR sensor is high
//                if(event.getState().isHigh() ){
//                    ledPin.high();
//                }else{
//                    ledPin.low();
//                }

            }
        });
//        // lets get this program running until user aborts (CTRL-C)
//        while(true){
//            Thread.sleep(500);
//        }

        // stop all GPIO activity/threads by shutting down the GPIO controller
//        gpio.shutdown();
    }

    @ResponseBody
    @RequestMapping(value = "/start-two", method = RequestMethod.GET)
    @Operation(summary = "Start 2", description = "Start 2", tags = {"Motion Sensor"})
    public void startTwo() {
        System.out.println("Starting Motion Sensor 2");

        //This is required to enable Non Privileged Access to avoid applying sudo to run Pi4j programs
        GpioUtil.enableNonPrivilegedAccess();

        final GpioController gpioPIRMotionSensor = GpioFactory.getInstance();
        final GpioPinDigitalInput pirMotionsensor = gpioPIRMotionSensor.provisionDigitalInputPin(RaspiPin.GPIO_26, PinPullResistance.PULL_DOWN);

        //Create gpio controller for LED listening on the pin GPIO_01 with default PinState as LOW
        final GpioController gpioLED = GpioFactory.getInstance();
        final GpioPinDigitalOutput led = gpioLED.provisionDigitalOutputPin(RaspiPin.GPIO_01,"LED",PinState.LOW);
        led.low();

        //Create and register gpio pin listener on PIRMotion Sensor GPIO Input instance
        pirMotionsensor.addListener(new GpioPinListenerDigital() {

            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
//if the event state is High then print "Intruder Detected" and turn the LED ON by invoking the high() method
                if(event.getState().isHigh()){
                    System.out.println("Intruder Detected!");
                    led.high();
                }
//if the event state is Low then print "All is quiet.." and make the LED OFF by invoking the low() method
                if(event.getState().isLow()){
                    System.out.println("All is quiet...");
                    led.low();
                }
            }
        });

        try {
            // keep program running until user aborts
            for (;;) {
                //Thread.sleep(500);
            }
        }
        catch (final Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
