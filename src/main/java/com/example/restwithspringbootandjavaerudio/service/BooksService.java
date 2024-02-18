package com.example.restwithspringbootandjavaerudio.service;
import com.example.restwithspringbootandjavaerudio.Excepitions.RequeiredObjectIsNullException;
import com.example.restwithspringbootandjavaerudio.Excepitions.ResorceNotFoundException;
import com.example.restwithspringbootandjavaerudio.controllers.BooksController;
import com.example.restwithspringbootandjavaerudio.data.vo.v1.BooksVO;
import com.example.restwithspringbootandjavaerudio.mapper.DozerMapper;
import com.example.restwithspringbootandjavaerudio.model.Books;
import com.example.restwithspringbootandjavaerudio.repositorys.BooksRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

@Service

public class BooksService {
    private Logger logger = Logger.getLogger(BooksService.class.getName());
    @Autowired
    private BooksRepository repository;


    public BooksVO findBooksById(Long id) throws Exception{
        logger.info("Find one book");
        var entity = repository.findById(id).orElseThrow(() -> new ResorceNotFoundException("No records for this id"));
        var vo = DozerMapper.parseObject(entity, BooksVO.class);
        vo.add(linkTo(methodOn(BooksController.class).findBooksById(id)).withSelfRel());
        return vo;
    }


    public List<BooksVO> findAll(){
        logger.info("Find all books");
        var books = DozerMapper.parseListObject(repository.findAll(), BooksVO.class);
        books.stream().forEach(b -> {
            try{
                b.add(linkTo(methodOn(BooksController.class).findBooksById(b.getKey())).withSelfRel());
            }catch (Exception e){
                throw new RuntimeException();
            }
        });
        return books;
    }


    public BooksVO create(BooksVO books) throws Exception{
        if (books == null){
            throw new RequeiredObjectIsNullException();
        }
        logger.info("Create one book");
        var entity = DozerMapper.parseObject(books, Books.class);
        var vo = DozerMapper.parseObject(repository.save(entity), BooksVO.class);
        vo.add(linkTo(methodOn(BooksController.class).findBooksById(vo.getKey())).withSelfRel());
        return vo;
    }

    public BooksVO update(BooksVO books) throws Exception{
        if (books == null){
            throw new RequeiredObjectIsNullException();
        }
        logger.info("update book");
        var entity = repository.findById(books.getKey()).orElseThrow(() -> new ResorceNotFoundException("No records for this id"));
        entity.setName(books.getName());
        entity.setAuthor(books.getAuthor());
        entity.setPublicationDate(books.getPublicationDate());
        var vo = DozerMapper.parseObject(repository.save(entity), BooksVO.class);
        vo.add(linkTo(methodOn(BooksController.class).findBooksById(vo.getKey())).withSelfRel());
        return vo;
    }

    public void delete(Long id){
        logger.info("delete book");
        var entity = repository.findById(id).orElseThrow(() -> new ResorceNotFoundException("No records for this id"));
        repository.delete(entity);
    }
}