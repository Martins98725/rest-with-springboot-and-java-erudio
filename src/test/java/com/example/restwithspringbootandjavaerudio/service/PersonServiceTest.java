package com.example.restwithspringbootandjavaerudio.service;
import com.example.restwithspringbootandjavaerudio.Excepitions.RequeiredObjectIsNullException;
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

import java.util.List;
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
        List<Person> list = input.mockEntityList();


        //quando houver a chamada findById, ele vai retornar um optionanl de entity
        when(repository.findAll()).thenReturn(list);
        var people = service.findAll();
        assertNotNull(people);
        assertEquals(14, people.size());

        var personOne = people.get(1);

        //Verifica se é nulo ou não
        assertNotNull(personOne);
        assertNotNull(personOne.getKey());
        assertNotNull(personOne.getLinks());
        assertTrue(personOne.toString().contains("[</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("Adress test: 1", personOne.getAddress());
        assertEquals("First name teste: 1", personOne.getFirstName());
        assertEquals("Last name teste: 1", personOne.getLastName());
        assertEquals("Female", personOne.getGender());

        var personFour = people.get(4);

        //Verifica se é nulo ou não
        assertNotNull(personFour);
        assertNotNull(personFour.getKey());
        assertNotNull(personFour.getLinks());
        assertTrue(personFour.toString().contains("[</api/person/v1/4>;rel=\"self\"]"));
        assertEquals("Adress test: 4", personFour.getAddress());
        assertEquals("First name teste: 4", personFour.getFirstName());
        assertEquals("Last name teste: 4", personFour.getLastName());
        assertEquals("Male", personFour.getGender());

        var personSeven = people.get(7);

        //Verifica se é nulo ou não
        assertNotNull(personSeven);
        assertNotNull(personSeven.getKey());
        assertNotNull(personSeven.getLinks());
        assertTrue(personSeven.toString().contains("[</api/person/v1/7>;rel=\"self\"]"));
        assertEquals("Adress test: 7", personSeven.getAddress());
        assertEquals("First name teste: 7", personSeven.getFirstName());
        assertEquals("Last name teste: 7", personSeven.getLastName());
        assertEquals("Female", personSeven.getGender());

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
    void createWithNullPerson() throws Exception{
        Exception exception = assertThrows(RequeiredObjectIsNullException.class, () -> {
            service.create(null);
        });
        String expectedMessage = "It is allowed to persist a null object";
        String actualdMessage = exception.getMessage();


        assertTrue(actualdMessage.contains(expectedMessage));

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
    void updateWithNullPerson(){
        Exception exception = assertThrows(RequeiredObjectIsNullException.class, () -> {
            service.update(null);
        });
        String expectedMessage = "It is allowed to persist a null object";
        String actualdMessage = exception.getMessage();


        assertTrue(actualdMessage.contains(expectedMessage));

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