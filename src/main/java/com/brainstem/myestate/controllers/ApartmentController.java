package com.brainstem.myestate.controllers;

import com.brainstem.myestate.payload.ApartmentDto;
import com.brainstem.myestate.service.ApartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/apartments")
public class ApartmentController {

    private ApartmentService apartmentService;

    public ApartmentController(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    //create apartment post rest api
    @PostMapping
    public ResponseEntity<ApartmentDto> createApartment(
            @RequestBody ApartmentDto apartmentDto
    ){
        return new ResponseEntity<>(apartmentService.createApartment(apartmentDto), HttpStatus.CREATED);
    }

    //get all apartments rest api
    @GetMapping
    public List<ApartmentDto> getAllApartments(
            @RequestParam(name = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize
    ){
        return apartmentService.getAllApartments(pageNumber, pageSize);
    }

    //get apartment by id
    @GetMapping("/{id}")
    public ResponseEntity<ApartmentDto> getApartmentByid(
            @PathVariable(name = "id") long id){
        return ResponseEntity.ok(apartmentService.getApartmentById(id));
    }

    //update apartment by id
    @PutMapping("/{id}")
//    @RequestMapping("/id", produces = "application/json", method = RequestMethod.PUT)
    public ResponseEntity<ApartmentDto> updateApartment(
            @PathVariable(name = "id") long id, @RequestBody ApartmentDto apartmentDto
    ){
        ApartmentDto updatedApartmentResponse =  apartmentService.updateApartment(id, apartmentDto);
        return new ResponseEntity<>(updatedApartmentResponse, HttpStatus.OK);
    }

    //delete apartment rest api
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteApartment(@PathVariable(name = "id") long id){
        apartmentService.deleteApartment(id);
        return new ResponseEntity<>("Apartment entity is deleted succesfully!", HttpStatus.OK);
    }
}
