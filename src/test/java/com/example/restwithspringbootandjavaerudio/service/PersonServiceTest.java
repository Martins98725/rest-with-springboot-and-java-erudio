package com.example.restwithspringbootandjavaerudio.service;
import com.example.restwithspringbootandjavaerudio.MockPerson;
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
        Person person = input.mockEntity(1);
        person.setId(1L);

        //quando houver a chamada findById, ele vai retornar um optionanl de person
        when(repository.findById(1L)).thenReturn(Optional.of(person));
        var result = service.findById(1L);

        //Verifica se é nulo ou não
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        //System.out.println(result.toString());
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
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void createV2() {
    }
}