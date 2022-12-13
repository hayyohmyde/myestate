package com.brainstem.myestate.controllers;

import com.brainstem.myestate.payload.ApartmentDto;
import com.brainstem.myestate.payload.ApartmentInputObject;
import com.brainstem.myestate.service.ApartmentService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApartmentController {

    private final ApartmentService apartmentService;

    public ApartmentController(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    //create apartment post rest api
//    @PreAuthorize("hasRole('USER')") //- To enable method level accessibility for this endpoint
//    @PostMapping(value = "/v1/apartments", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<?> createApartment(
//            @RequestBody ApartmentDto apartmentDto,
//            @RequestParam("files") MultipartFile[] files
//            ) throws Exception {
//        return new ResponseEntity<>(apartmentService.createApartment(apartmentDto, files), HttpStatus.CREATED);
//    }

//    @PostMapping(value = "/v1/apartments", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<?> createApartment(
//             ApartmentDto apartmentDto
//            ) throws Exception {
//        System.out.println("Form input => " + apartmentDto.getImages().get(0));
//        return new ResponseEntity<>(apartmentService.createApartments(apartmentDto), HttpStatus.CREATED);
//    }

    @PostMapping(value = "/v1/apartments", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createApartment(ApartmentInputObject apartmentInputObject) throws Exception {
        System.out.println("Form input => " + apartmentInputObject.getFiles()[0]);
        return new ResponseEntity<>(apartmentService.createApartment(apartmentInputObject), HttpStatus.CREATED);
    }

    //get all apartments rest api
    @GetMapping("/v1/apartments")
    public ResponseEntity<List<ApartmentDto>> getAllApartments(
            @RequestParam(name = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize
    ){
        return ResponseEntity.ok(apartmentService.getAllApartments(pageNumber, pageSize));
    }

    //search apartments rest api
    @ApiOperation("Search api for apartments")
    @GetMapping("/v1/apartments/search")
    public ResponseEntity<List<ApartmentDto>> searchApartments(@RequestParam("query") String query){
        return ResponseEntity.ok(apartmentService.searchApartments(query));
    }

    //get apartment by id
    @GetMapping("/v1/apartments/{id}")
    public ResponseEntity<ApartmentDto> getApartmentByid(
            @PathVariable(name = "id") long id){
        return ResponseEntity.ok(apartmentService.getApartmentById(id));
    }

    //update apartment by id
    @PutMapping("/v1/apartments/{id}")
//    @RequestMapping("/id", produces = "application/json", method = RequestMethod.PUT)
    public ResponseEntity<ApartmentDto> updateApartment(
            @PathVariable(name = "id") long id, @RequestBody ApartmentInputObject apartmentInputObject
    ){
        ApartmentDto updatedApartmentResponse =  apartmentService.updateApartment(id, apartmentInputObject);
        return new ResponseEntity<>(updatedApartmentResponse, HttpStatus.OK);
    }

    //delete apartment rest api
    @DeleteMapping("/v1/apartments/{id}")
    public ResponseEntity<String> deleteApartment(@PathVariable(name = "id") long id){
        apartmentService.deleteApartment(id);
        return new ResponseEntity<>("Apartment entity is deleted succesfully!", HttpStatus.OK);
    }
}
