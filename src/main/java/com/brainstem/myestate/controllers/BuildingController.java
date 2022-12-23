package com.brainstem.myestate.controllers;

import com.brainstem.myestate.dto.request.BuildingDto;
import com.brainstem.myestate.dto.response.BuildingDtoResponse;
import com.brainstem.myestate.service.BuildingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BuildingController {

        private final BuildingService buildingService;

        public BuildingController(BuildingService buildingService) {
            this.buildingService = buildingService;
        }

        //create building post rest api
        @PreAuthorize("hasRole('USER')")
        @PostMapping(value = "/v1/buildings", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<BuildingDtoResponse> createBuilding(BuildingDto buildingDto) throws Exception {
            return new ResponseEntity<>(buildingService.createBuilding(buildingDto), HttpStatus.CREATED);
        }

        //get all buildings rest api
        @GetMapping("/v1/buildings")
        public List<BuildingDtoResponse> getAllBuildings(
                @RequestParam(name = "pageNumber", defaultValue = "0", required = false) int pageNumber,
                @RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize
        ){
            return buildingService.getAllBuildings(pageNumber, pageSize);
        }

        //get building by id
        @GetMapping("/v1/buildings/{id}")
        public ResponseEntity<BuildingDtoResponse> getBuildingByid( @PathVariable(name = "id") long id){
            return ResponseEntity.ok(buildingService.getBuildingById(id));
        }

        //update building by id
        @PreAuthorize("hasRole('USER')")
        @PutMapping(value = "/v1/buildings/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<BuildingDtoResponse> updateBuilding( @PathVariable(name = "id") long id, BuildingDto buildingDto) throws Exception {
            return new ResponseEntity<>(buildingService.updateBuilding(id, buildingDto), HttpStatus.OK);
        }

        //delete apartment rest api
        @PreAuthorize("hasRole('USER')")
        @DeleteMapping("/v1/buildings/{id}")
        public ResponseEntity<String> deleteApartment(@PathVariable(name = "id") long id){
            buildingService.deleteBuilding(id);
            return new ResponseEntity<>("Building entity is deleted succesfully!", HttpStatus.OK);
        }
}
