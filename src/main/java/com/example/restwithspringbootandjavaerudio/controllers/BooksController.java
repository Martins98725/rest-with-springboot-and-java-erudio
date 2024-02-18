package com.example.restwithspringbootandjavaerudio.controllers;
import com.example.restwithspringbootandjavaerudio.data.vo.v1.BooksVO;
import com.example.restwithspringbootandjavaerudio.service.BooksService;
import com.example.restwithspringbootandjavaerudio.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books/v1")
@Tag(name = "Books", description = "Endpoints for managing books")
public class BooksController {
    @Autowired
    private BooksService service;


    @Operation(summary = "Find Book by id", description = "Finds all people", tags = {"Book"}, responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content(
                    schema = @Schema(implementation = BooksVO.class)
            )),
            @ApiResponse(description = "No content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal server error", responseCode = "500", content = @Content)
    })

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public BooksVO findBooksById(@PathVariable(value = "id") Long id)throws Exception{
        return service.findBooksById(id);
    }

    @Operation(summary = "Finds all Book", description = "Finds all books", tags = {"Books"}, responses = {
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

    @GetMapping(produces ={MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public List<BooksVO>findAll(){
        return service.findAll();
    }

    @Operation(summary = "Adds a new book", description = "Adds a new book by passing in a Json, XML, YML representation of the book ", tags = {"Books"}, responses = {
            @ApiResponse(description = "Created", responseCode = "200", content = @Content(
                    schema = @Schema(implementation = BooksVO.class)
            )),
            @ApiResponse(description = "Bad request", responseCode = "204", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Internal server error", responseCode = "500", content = @Content)
    })

    @PostMapping(consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_YML}, produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public BooksVO create(@RequestBody BooksVO books) throws Exception{
        return service.create(books);
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

    @PutMapping(consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_YML},produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public BooksVO update(@RequestBody BooksVO books) throws Exception{
        return service.update(books);
    }

    @Operation(summary = "Delete a book", description = "delete a person by passing in a Json, XML, YML representation of book", tags = {"Books"}, responses = {
            @ApiResponse(description = "No content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal server error", responseCode = "500", content = @Content)
    })
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
