package com.brainstem.myestate.controllers;


import com.brainstem.myestate.dto.request.LandDto;
import com.brainstem.myestate.dto.response.LandDtoResponse;
import com.brainstem.myestate.service.LandService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LandController {
    private final LandService landService;

    public LandController(LandService landService) {
        this.landService = landService;
    }

    //create land post rest api
    @PreAuthorize("hasRole('USER')")
    @PostMapping(value = "/v1/lands", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LandDtoResponse> createNewLand(LandDto landDto) throws Exception {
        return new ResponseEntity<>(landService.createLand(landDto), HttpStatus.CREATED);
    }

    //get all lands rest api
    @GetMapping("/v1/lands")
    public ResponseEntity<List<LandDtoResponse>> getAllLands(
            @RequestParam(name = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize
    ){
        return ResponseEntity.ok().body(landService.getAllLands(pageNumber, pageSize));
    }

    //get lands by id
    @GetMapping("/v1/lands/{id}")
    public ResponseEntity<LandDtoResponse> getLandById(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(landService.getLandById(id));
    }

    //update apartment by id
    @PreAuthorize("hasRole('USER')")
    @PatchMapping(value = "/v1/lands/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LandDtoResponse> updateLand( @PathVariable(name = "id") long id, LandDto landDto) throws Exception {
        return ResponseEntity.ok(landService.updateLand(id, landDto));
    }

    //delete land rest api
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/v1/lands/{id}")
    public ResponseEntity<String> deleteLand(@PathVariable(name = "id") long id){
        landService.deleteLand(id);
        return new ResponseEntity<>("Land entity is deleted succesfully!", HttpStatus.OK);
    }
}
