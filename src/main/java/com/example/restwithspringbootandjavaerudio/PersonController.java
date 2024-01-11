package com.example.restwithspringbootandjavaerudio;

import com.example.restwithspringbootandjavaerudio.data.vo.v1.PersonVO;
import com.example.restwithspringbootandjavaerudio.data.vo.v2.PersonVOV2;
import com.example.restwithspringbootandjavaerudio.service.PersonService;
import com.example.restwithspringbootandjavaerudio.util.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person/v1")
public class PersonController {
    @Autowired
    private PersonService service;
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public PersonVO findById(@PathVariable(value = "id") Long id)throws Exception {
       return service.findById(id);
    }
    @GetMapping(produces ={MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public List<PersonVO> findByAll(){
        return service.findAll();
    }
    @PostMapping( consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_YML}, produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public PersonVO create(@RequestBody PersonVO person) throws Exception{
        return service.create(person);
    }
    @PutMapping(consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_YML},produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public PersonVO update(@RequestBody PersonVO person) throws Exception{

        return service.update(person);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id)throws Exception {
         service.delete(id);
         return ResponseEntity.noContent().build();
    }
    @PostMapping(value = "/v2",consumes = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_YML}, produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public PersonVOV2 createV2(@RequestBody PersonVOV2 person){
        return service.createV2(person);
    }
}
