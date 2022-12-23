package com.brainstem.myestate.controllers;

import com.brainstem.myestate.dto.request.ApartmentDto;
import com.brainstem.myestate.dto.response.ApartmentDtoResponse;
import com.brainstem.myestate.service.ApartmentService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApartmentController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private final ApartmentService apartmentService;

    public ApartmentController(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }


    @PreAuthorize("hasRole('USER')")
    @PostMapping(value = "/v1/apartments", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApartmentDtoResponse> createApartment(ApartmentDto apartmentInputObject) throws Exception {
        return new ResponseEntity<>(apartmentService.createApartment(apartmentInputObject), HttpStatus.CREATED);
    }

    //get all apartments rest api
    @GetMapping(value = "/v1/apartments")
    public ResponseEntity<List<ApartmentDtoResponse>> getAllApartments(
            @RequestParam(name = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize
    ){
        return ResponseEntity.ok(apartmentService.getAllApartments(pageNumber, pageSize));
    }

    //search apartments rest api
    @ApiOperation("Search api for apartments")
    @GetMapping("/v1/apartments/search")
    public ResponseEntity<List<ApartmentDtoResponse>> searchApartments(@RequestParam("query") String query){
        return ResponseEntity.ok(apartmentService.searchApartments(query));
    }

    //get apartment by id
    @GetMapping("/v1/apartments/{id}")
    public ResponseEntity<ApartmentDtoResponse> getApartmentByid(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(apartmentService.getApartmentById(id));
    }

    //update apartment by id
    @PreAuthorize("hasRole('USER')")
    @PatchMapping(value = "/v1/apartments/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApartmentDtoResponse> updateApartment(@PathVariable(name = "id") long id, ApartmentDto apartmentInputObject){
        return ResponseEntity.ok().body(apartmentService.updateApartment(id, apartmentInputObject));
    }

    //delete apartment rest api
    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/v1/apartments/{id}")
    public ResponseEntity<String> deleteApartment(@PathVariable(name = "id") long id){
        apartmentService.deleteApartment(id);
        return new ResponseEntity<>("Apartment with id " + id +" is deleted succesfully!", HttpStatus.OK);
    }
}
