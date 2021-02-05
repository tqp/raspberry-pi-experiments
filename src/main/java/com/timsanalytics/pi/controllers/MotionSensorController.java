package com.timsanalytics.pi.controllers;

import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
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
    @RequestMapping(value = "/start", method = RequestMethod.GET)
    @Operation(summary = "Start", description = "Start", tags = {"Motion Sensor"})
    public void start() {
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
        pir.addListener(new GpioPinListenerDigital(){
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event){
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

}
