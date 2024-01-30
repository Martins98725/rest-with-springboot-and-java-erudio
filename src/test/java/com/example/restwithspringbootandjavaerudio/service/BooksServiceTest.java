package com.example.restwithspringbootandjavaerudio.service;
import com.example.restwithspringbootandjavaerudio.Excepitions.RequeiredObjectIsNullException;
import com.example.restwithspringbootandjavaerudio.MockBook;
import com.example.restwithspringbootandjavaerudio.data.vo.v1.BooksVO;
import com.example.restwithspringbootandjavaerudio.model.Books;
import com.example.restwithspringbootandjavaerudio.repositorys.BooksRepository;
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
class BooksServiceTest {

    MockBook input;
    @InjectMocks
    private BooksService service;

    @Mock
    BooksRepository repository;
    @BeforeEach
    void setUpMocks() throws Exception{
        input = new MockBook();

        MockitoAnnotations.openMocks(this);
    }


    @Test
    void findBooksById() throws Exception{
        Books entity = input.mockEntity(1);
        entity.setId(1L);

        //quando houver a chamada findById, ele vai retornar um optionanl de entity
        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        var result = service.findBooksById(1L);

        //Verifica se é nulo ou não
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("[</api/books/v1/1>;rel=\"self\"]"));
        assertEquals("Name of the book test: 1", result.getName());
        assertEquals("Name of author test: 1", result.getAuthor());
        assertEquals("Publication date test: 1", result.getPublicationDate());
    }

    @Test
    void findAll() {

        List<Books> list = input.mockEntityList();


        //quando houver a chamada findById, ele vai retornar um optionanl de entity
        when(repository.findAll()).thenReturn(list);
        var books = service.findAll();
        assertNotNull(books);
        assertEquals(14, books.size());

        var booksOne = books.get(1);

        //Verifica se é nulo ou não
        assertNotNull(booksOne);
        assertNotNull(booksOne.getKey());
        assertNotNull(booksOne.getLinks());
        assertTrue(booksOne.toString().contains("[</api/books/v1/1>;rel=\"self\"]"));
        assertEquals("Name of the book test: 1", booksOne.getName());
        assertEquals("Name of author test: 1", booksOne.getAuthor());
        assertEquals("Publication date test: 1", booksOne.getPublicationDate());

        var bookFour = books.get(4);
        //Verifica se é nulo ou não
        assertNotNull(bookFour);
        assertNotNull(bookFour.getKey());
        assertNotNull(bookFour.getLinks());
        assertTrue(bookFour.toString().contains("[</api/books/v1/4>;rel=\"self\"]"));
        assertEquals("Name of the book test: 4", bookFour.getName());
        assertEquals("Name of author test: 4", bookFour.getAuthor());
        assertEquals("Publication date test: 4", bookFour.getPublicationDate());


        var bookSeven = books.get(7);
        //Verifica se é nulo ou não
        assertNotNull(bookSeven);
        assertNotNull(bookSeven.getKey());
        assertNotNull(bookSeven.getLinks());
        assertTrue(bookSeven.toString().contains("[</api/books/v1/7>;rel=\"self\"]"));
        assertEquals("Name of the book test: 7", bookSeven.getName());
        assertEquals("Name of author test: 7", bookSeven.getAuthor());
        assertEquals("Publication date test: 7", bookSeven.getPublicationDate());


    }

    @Test
    void create() throws Exception{
        Books entity = input.mockEntity(1);
        Books persisted = entity;

        persisted.setId(1L);

        BooksVO vo = input.mockVO(1);
        vo.setKey(1L);

        //quando houver a chamada findById, ele vai retornar um optionanl de person
        when(repository.save(entity)).thenReturn(persisted);

        var result = service.create(vo);

        //Verifica se é nulo ou não
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("[</api/books/v1/1>;rel=\"self\"]"));
        assertEquals("Name of the book test: 1", result.getName());
        assertEquals("Name of author test: 1", result.getAuthor());
        assertEquals("Publication date test: 1", result.getPublicationDate());

    }

    @Test
    void update() throws Exception{

        Books entity = input.mockEntity(1);
        Books persisted = entity;

        persisted.setId(1L);

        BooksVO vo = input.mockVO(1);
        vo.setKey(1L);

        //quando houver a chamada findById, ele vai retornar um optionanl de person
        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(repository.save(entity)).thenReturn(persisted);

        var result = service.update(vo);

        //Verifica se é nulo ou não
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("[</api/books/v1/1>;rel=\"self\"]"));
        assertEquals("Name of the book test: 1", result.getName());
        assertEquals("Name of author test: 1", result.getAuthor());
        assertEquals("Publication date test: 1", result.getPublicationDate());
    }

    @Test
    void createWithNullBook() throws Exception{
        Exception exception = assertThrows(RequeiredObjectIsNullException.class, () -> {
            service.create(null);
        });
        String expectedMessage = "It isn`t allowed to persist a null object";
        String actualdMessage = exception.getMessage();


        assertTrue(actualdMessage.contains(expectedMessage));

    }
    @Test
    void updateWithNullPerson(){
        Exception exception = assertThrows(RequeiredObjectIsNullException.class, () -> {
            service.update(null);
        });
        String expectedMessage = "It isn`t allowed to persist a null object";
        String actualdMessage = exception.getMessage();


        assertTrue(actualdMessage.contains(expectedMessage));

    }
    @Test
    void delete(){
        Books entity = input.mockEntity(1);
        entity.setId(1L);

        //quando houver a chamada findById, ele vai retornar um optionanl de entity
        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        service.delete(1L);
    }
}