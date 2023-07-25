package com.example.restwithspringbootandjavaerudio;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreentingController {

    //so pra da commit
    //2
    //3
    private static final String template = "Hello, %s!";
    private static final AtomicLong counter = new AtomicLong();
@RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "world") String name){
        return new com.example.restwithspringbootandjavaerudio.Greeting(counter.incrementAndGet() , String.format(template, name));
    }

}
