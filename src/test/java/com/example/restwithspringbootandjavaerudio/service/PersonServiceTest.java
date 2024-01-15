package com.example.restwithspringbootandjavaerudio.service;
import com.example.restwithspringbootandjavaerudio.MockPerson;
import com.example.restwithspringbootandjavaerudio.data.vo.v1.PersonVO;
import com.example.restwithspringbootandjavaerudio.model.Person;
import com.example.restwithspringbootandjavaerudio.repositorys.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    MockPerson input;

    @InjectMocks
    private PersonService service;

    @Mock
    PersonRepository repository;
    @BeforeEach
    void setUpMocks() throws Exception{
        input = new MockPerson();

        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById() throws Exception{
        Person entity = input.mockEntity(1);
        entity.setId(1L);

        //quando houver a chamada findById, ele vai retornar um optionanl de entity
        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        var result = service.findById(1L);

        //Verifica se é nulo ou não
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("[</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("Adress test: 1", result.getAddress());
        assertEquals("First name teste: 1", result.getFirstName());
        assertEquals("Last name teste: 1", result.getLastName());
        assertEquals("Female", result.getGender());

    }

    @Test
    void findAll() {
    }

    @Test
    void create() throws Exception {
        Person entity = input.mockEntity(1);
        Person persisted = entity;

        persisted.setId(1L);

        PersonVO vo = input.mockVO(1);
        vo.setKey(1L);

        //quando houver a chamada findById, ele vai retornar um optionanl de person
        when(repository.save(entity)).thenReturn(persisted);

        var result = service.create(vo);

        //Verifica se é nulo ou não
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("[</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("Adress test: 1", result.getAddress());
        assertEquals("First name teste: 1", result.getFirstName());
        assertEquals("Last name teste: 1", result.getLastName());
        assertEquals("Female", result.getGender());

    }

    @Test
    void update() throws Exception{
        Person entity = input.mockEntity(1);
        entity.setId(1L);
        Person persisted = entity;

        persisted.setId(1L);

        PersonVO vo = input.mockVO(1);
        vo.setKey(1L);

        //quando houver a chamada findById, ele vai retornar um optionanl de person
        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(repository.save(entity)).thenReturn(persisted);

        var result = service.update(vo);

        //Verifica se é nulo ou não
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("[</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("Adress test: 1", result.getAddress());
        assertEquals("First name teste: 1", result.getFirstName());
        assertEquals("Last name teste: 1", result.getLastName());
        assertEquals("Female", result.getGender());
    }

    @Test
    void delete(){
        Person entity = input.mockEntity(1);
        entity.setId(1L);

        //quando houver a chamada findById, ele vai retornar um optionanl de entity
        when(repository.findById(1L)).thenReturn(Optional.of(entity));
         service.delete(1L);
    }
}