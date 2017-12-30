package com.nickjb2017.pi4java.controller;

import com.pi4j.io.gpio.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LedController {

    private GpioPinDigitalOutput pin;
    private boolean light = false;

    @RequestMapping("/")
    public String greeting() {
        return "Hello World!";
    }

    @RequestMapping("/light")
    public String light() {
        if (pin == null) {
            GpioController gpio = GpioFactory.getInstance();
            pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_07, "MyLED", PinState.LOW);
        }

        String retString;

        if(light) {
            pin.high();
            light = false;
            retString = "LIGHT ON";
        }
        else {
            pin.low();
            light = true;
            retString = "LIGHT OFF";
        }

        return retString;
    }

}
