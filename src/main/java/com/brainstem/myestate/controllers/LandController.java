package com.brainstem.myestate.controllers;


import com.brainstem.myestate.payload.LandDto;
import com.brainstem.myestate.service.LandService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LandController {
    private LandService landService;

    public LandController(LandService landService) {
        this.landService = landService;
    }


    //create land post rest api
    @PostMapping("/v1/lands")
    public ResponseEntity<LandDto> createLand(
            @RequestBody LandDto landDto
    ){
        return new ResponseEntity<>(landService.createLand(landDto), HttpStatus.CREATED);
    }

    //get all lands rest api
    @GetMapping("/v1/lands")
    public List<LandDto> getAllLands(
            @RequestParam(name = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize
    ){
        return landService.getAllLands(pageNumber, pageSize);
    }

    //get lands by id
    @GetMapping("/v1/lands/{id}")
    public ResponseEntity<LandDto> getLandByid(
            @PathVariable(name = "id") long id){
        return ResponseEntity.ok(landService.getLandById(id));
    }

    //update apartment by id
    @PutMapping("/v1/lands/{id}")
//    @RequestMapping("/id", produces = "application/json", method = RequestMethod.PUT)
    public ResponseEntity<LandDto> updateLand(
            @PathVariable(name = "id") long id, @RequestBody LandDto landDto
    ){
        LandDto updatedApartmentResponse =  landService.updateLand(id, landDto);
        return new ResponseEntity<>(updatedApartmentResponse, HttpStatus.OK);
    }

    //delete land rest api
    @DeleteMapping("/v1/lands/{id}")
    public ResponseEntity<String> deleteLand(@PathVariable(name = "id") long id){
        landService.deleteLand(id);
        return new ResponseEntity<>("Land entity is deleted succesfully!", HttpStatus.OK);
    }
}
