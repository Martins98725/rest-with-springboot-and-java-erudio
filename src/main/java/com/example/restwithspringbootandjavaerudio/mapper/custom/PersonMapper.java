package com.example.restwithspringbootandjavaerudio.mapper.custom;

import com.example.restwithspringbootandjavaerudio.data.vo.v2.PersonVOV2;
import com.example.restwithspringbootandjavaerudio.model.Person;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonMapper {
    public PersonVOV2 convertEntityToVO(Person person){
        PersonVOV2 vo = new PersonVOV2();
        vo.setId(person.getId());
        vo.setAddress(person.getAddress());
        vo.setBirthDay(new Date());
        vo.setGender(person.getGender());
        vo.setFirstName(person.getFirstName());
        vo.setLastName(person.getLastName());

        return vo;
    }
    public Person convertVOToEntity(PersonVOV2 person){
        Person entity = new Person();
        entity.setId(person.getId());
        entity.setAddress(person.getAddress());
        //entity.setBirthDay(new Date());
        entity.setGender(person.getGender());
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());

        return entity;
    }
}
