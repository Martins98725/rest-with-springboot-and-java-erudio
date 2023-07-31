package com.example.restwithspringbootandjavaerudio.service;

import com.example.restwithspringbootandjavaerudio.model.Person;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;
@Service
public class PersonService {
    private final AtomicLong couter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonService.class.getName());

    public Person findById(String id){
        logger.info("Finding one person");
        Person person = new Person();
        person.setId(couter.incrementAndGet());
        person.setFirstName("Ícaro");
        person.setLastName("Martins");
        person.setGender("Male");
        person.setAddress("São luis - Maranhão - Brasil");
        return person;
    }
}
