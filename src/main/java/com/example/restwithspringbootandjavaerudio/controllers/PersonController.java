package com.example.restwithspringbootandjavaerudio.controllers;

import com.example.restwithspringbootandjavaerudio.data.vo.v1.PersonVO;
import com.example.restwithspringbootandjavaerudio.data.vo.v2.PersonVOV2;
import com.example.restwithspringbootandjavaerudio.service.PersonService;
import com.example.restwithspringbootandjavaerudio.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person/v1")
@Tag(name = "People", description = "Endpoints for managing people")
public class PersonController {
    @Autowired
    private PersonService service;
    //Seria interessante colocar essa regras de negocios como hateos e swagger no service
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Finds a person", description = "Finds a person", tags = {"People"}, responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content(
                    schema = @Schema(implementation = PersonVO.class)
            )),
            @ApiResponse(description = "No content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal server error", responseCode = "500", content = @Content)
    })
    public PersonVO findById(@PathVariable(value = "id") Long id)throws Exception {
       return service.findById(id);
    }
    @GetMapping(produces ={MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Finds all people", description = "Finds all people", tags = {"People"}, responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = {@Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = PersonVO.class))
            )
            }),
            @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal server error", responseCode = "500", content = @Content)
    })
    public List<PersonVO> findByAll(){
        return service.findAll();
    }

    @PostMapping( consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_YML}, produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Adds a new person", description = "Adds a new person by passing in a Json, XML, YML representation of the people ", tags = {"People"}, responses = {
            @ApiResponse(description = "Created", responseCode = "200", content = @Content(
                    schema = @Schema(implementation = PersonVO.class)
            )),
            @ApiResponse(description = "Bad request", responseCode = "204", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Internal server error", responseCode = "500", content = @Content)
    })
    public PersonVO create(@RequestBody PersonVO person) throws Exception{
        return service.create(person);
    }
    @PutMapping(consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_YML},produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Updates a person", description = "Updates a person by passing in a Json, XML, YML representation of the people", tags = {"People"}, responses = {
            @ApiResponse(description = "Updated", responseCode = "200", content = @Content(
                    schema = @Schema(implementation = PersonVO.class)
            )),
            @ApiResponse(description = "Bad request", responseCode = "204", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal server error", responseCode = "500", content = @Content)
    })
    public PersonVO update(@RequestBody PersonVO person) throws Exception{

        return service.update(person);
    }
    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete a person", description = "delete a person by passing in a Json, XML, YML representation of ", tags = {"People"}, responses = {
            @ApiResponse(description = "No content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal server error", responseCode = "500", content = @Content)
    })
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
         service.delete(id);
         return ResponseEntity.noContent().build();
    }
    @PostMapping(value = "/v2",consumes = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_YML}, produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public PersonVOV2 createV2(@RequestBody PersonVOV2 person){
        return service.createV2(person);
    }
}
