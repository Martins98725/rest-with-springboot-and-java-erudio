package com.example.restwithspringbootandjavaerudio.service;

import com.example.restwithspringbootandjavaerudio.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
    public List<Person> findAll(){
        logger.info("Finding all people");

        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            Person person = mockPerson(i);
            persons.add(person);
        }
        return persons;
    }
    public Person create(Person person){
        logger.info("create one person");
        return person;
    }
    public Person update(Person person){
        logger.info("create one person");
        return person;
    }
    public void delete(String id){
        logger.info("deleting one person");
    }
    private Person mockPerson(int i) {
        Person person = new Person();
        person.setId(couter.incrementAndGet());
        person.setFirstName("Person name: " + i);
        person.setLastName("Person last name: " + i);
        person.setGender("Person gender: " + i);
        person.setAddress("Person addres: " + i);
        return person;
    }
}
