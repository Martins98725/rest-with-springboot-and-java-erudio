package com.example.restwithspringbootandjavaerudio.controllers;

import com.example.restwithspringbootandjavaerudio.data.vo.v1.BooksVO;
import com.example.restwithspringbootandjavaerudio.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BooksController {
    @Autowired
    private BooksService service;
    @GetMapping("/{id}")
    public BooksVO findBooksById(@PathVariable(value = "id") Long id)throws Exception{
        return service.findBooksById(id);
    }
    public List<BooksVO>findAll(){
        return service.findAll();
    }
}
