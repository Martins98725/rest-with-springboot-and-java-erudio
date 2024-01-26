package com.example.restwithspringbootandjavaerudio.controllers;
import com.example.restwithspringbootandjavaerudio.data.vo.v1.BooksVO;
import com.example.restwithspringbootandjavaerudio.service.BooksService;
import com.example.restwithspringbootandjavaerudio.util.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books/v1")
public class BooksController {
    @Autowired
    private BooksService service;
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public BooksVO findBooksById(@PathVariable(value = "id") Long id)throws Exception{
        return service.findBooksById(id);
    }
    @GetMapping(produces ={MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public List<BooksVO>findAll(){
        return service.findAll();
    }
    @PostMapping(consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_YML}, produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public BooksVO create(@RequestBody BooksVO books) throws Exception{
        return service.create(books);
    }
    @PutMapping(consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_YML},produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public BooksVO update(@RequestBody BooksVO books) throws Exception{
        return service.update(books);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
