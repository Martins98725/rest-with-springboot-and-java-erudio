package com.example.restwithspringbootandjavaerudio;

import com.example.restwithspringbootandjavaerudio.data.vo.v1.PersonVO;
import com.example.restwithspringbootandjavaerudio.model.Person;

import java.util.ArrayList;
import java.util.List;

public class MockPerson {
    public Person mockEntity(){
        return mockEntity(0);
    }

    public PersonVO mockVO(){
        return mockVO(0);
    }

    public List<Person> mockEntityList(){
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < 14; i++){
            persons.add(mockEntity(i));
        }
        return persons;
    }




    public Person mockEntity(Integer number) {
        Person person = new Person();

        person.setAddress("Adress test: " + number);
        person.setFirstName("First name teste: " + number);
        person.setGender(((number % 2) == 0) ? "Male" : "Female");
        person.setId(number.longValue());
        person.setLastName("Last name teste: " + number);
        return person;
    }
    public PersonVO mockVO(Integer number){
        PersonVO person = new PersonVO();

        person.setAddress("Adress test: " + number);
        person.setFirstName("First name teste: " + number);
        person.setGender(((number % 2) == 0) ? "Male" : "Female");
        person.setKey(number.longValue());
        person.setLastName("Last name teste: " + number);
        return person;
    }
}
