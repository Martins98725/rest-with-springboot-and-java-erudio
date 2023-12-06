package com.example.restwithspringbootandjavaerudio.service;

import com.example.restwithspringbootandjavaerudio.Excepitions.ResorceNotFoundException;
import com.example.restwithspringbootandjavaerudio.PersonController;
import com.example.restwithspringbootandjavaerudio.data.vo.v1.PersonVO;
import com.example.restwithspringbootandjavaerudio.data.vo.v2.PersonVOV2;
import com.example.restwithspringbootandjavaerudio.mapper.DozerMapper;
import com.example.restwithspringbootandjavaerudio.mapper.custom.PersonMapper;
import com.example.restwithspringbootandjavaerudio.model.Person;
import com.example.restwithspringbootandjavaerudio.repositorys.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import java.util.List;
import java.util.logging.Logger;
@Service
public class PersonService {
    private Logger logger = Logger.getLogger(PersonService.class.getName());
    @Autowired
    PersonRepository repository;

    @Autowired
    PersonMapper mapper;

    public PersonVO findById(Long id) throws Exception{
        logger.info("Finding one person");

       var entity = repository.findById(id)
               .orElseThrow(() -> new ResorceNotFoundException("No records found for this id!!"));
       var vo = DozerMapper.parseObject(entity, PersonVO.class);
       vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
       return vo;
    }
    public List<PersonVO> findAll() {
        logger.info("Finding all people");
        var persons =  DozerMapper.parseListObject(repository.findAll(), PersonVO.class);
        persons.stream().forEach(p -> {
            try {
                p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return persons;
    }
    public PersonVO create(PersonVO person) throws Exception {
        logger.info("create one person");
        var entity = DozerMapper.parseObject(person, Person.class);
        var vo = DozerMapper.parseObject(repository.save(entity),  PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }
    public PersonVO update(PersonVO person) throws Exception{
        logger.info("create one person");
        var entity = repository.findById(person.getKey()).orElseThrow(() -> new ResorceNotFoundException("No records found for this id!!"));
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setGender(person.getGender());
        entity.setAddress(person.getAddress());
        var vo = DozerMapper.parseObject(repository.save(entity),  PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }
    public void delete(Long id){
        logger.info("deleting one person");
        var entity = repository.findById(id).orElseThrow(() -> new ResorceNotFoundException("No records found for this id!!"));
        repository.delete(entity);
    }

    public PersonVOV2 createV2(PersonVOV2 person){
        logger.info("create one person");
        var entity = mapper.convertVOToEntity(person);
        var vo = mapper.convertEntityToVO(repository.save(entity));
        return vo;
    }
}
