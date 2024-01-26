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

    @Operation(summary = "Finds all people", description = "Finds all people", tags = {"People"}, responses = {
            @ApiResponse(description = "Secuss", responseCode = "200", content = @Content(
                    schema = @Schema(implementation = BooksVO.class)
            )),
            @ApiResponse(description = "No content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal server error", responseCode = "500", content = @Content)
    })
    public BooksVO findBooksById(Long id) throws Exception{
        logger.info("Find one book");
        var entity = repository.findById(id).orElseThrow(() -> new ResorceNotFoundException("No records for this id"));
        var vo = DozerMapper.parseObject(entity, BooksVO.class);
        vo.add(linkTo(methodOn(BooksController.class).findBooksById(id)).withSelfRel());
        return vo;
    }

    @Operation(summary = "Finds all people", description = "Finds all books", tags = {"Books"}, responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = {@Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = BooksVO.class))
            )
            }),
            @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal server error", responseCode = "500", content = @Content)
    })
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

    @Operation(summary = "Adds a new book", description = "Adds a new book by passing in a Json, XML, YML representation of the book ", tags = {"Books"}, responses = {
            @ApiResponse(description = "Created", responseCode = "200", content = @Content(
                    schema = @Schema(implementation = BooksVO.class)
            )),
            @ApiResponse(description = "Bad request", responseCode = "204", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Internal server error", responseCode = "500", content = @Content)
    })
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
    @Operation(summary = "Updates a book", description = "Updates a person by passing in a Json, XML, YML representation of the books", tags = {"Book"}, responses = {
            @ApiResponse(description = "Updated", responseCode = "200", content = @Content(
                    schema = @Schema(implementation = BooksVO.class)
            )),
            @ApiResponse(description = "Bad request", responseCode = "204", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal server error", responseCode = "500", content = @Content)
    })
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
    @Operation(summary = "Delete a book", description = "delete a person by passing in a Json, XML, YML representation of book", tags = {"Books"}, responses = {
            @ApiResponse(description = "No content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal server error", responseCode = "500", content = @Content)
    })
    public void delete(Long id){
        logger.info("delete book");
        var entity = repository.findById(id).orElseThrow(() -> new ResorceNotFoundException("No records for this id"));
        repository.delete(entity);
    }
}