package com.example.restwithspringbootandjavaerudio.service;

import com.example.restwithspringbootandjavaerudio.Excepitions.ResorceNotFoundException;
import com.example.restwithspringbootandjavaerudio.model.Person;
import com.example.restwithspringbootandjavaerudio.repositorys.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;
@Service
public class PersonService {
    private Logger logger = Logger.getLogger(PersonService.class.getName());
    @Autowired
    PersonRepository repository;

    public Person findById(Long id){
        logger.info("Finding one person");
        Person person = new Person();
        return repository.findById(id).orElseThrow(() -> new ResorceNotFoundException("No records found for this id!!"));
    }
    public List<Person> findAll(){
        logger.info("Finding all people");
        return repository.findAll();
    }
    public Person create(Person person){
        logger.info("create one person");
        return repository.save(person);
    }
    public Person update(Person person){
        logger.info("create one person");
        Person entity = repository.findById(person.getId()).orElseThrow(() -> new ResorceNotFoundException("No records found for this id!!"));
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setGender(person.getGender());
        entity.setAddress(person.getAddress());

        return repository.save(person);
    }
    public void delete(Long id){
        logger.info("deleting one person");
        Person entity = repository.findById(id).orElseThrow(() -> new ResorceNotFoundException("No records found for this id!!"));
        repository.delete(entity);
    }
}
