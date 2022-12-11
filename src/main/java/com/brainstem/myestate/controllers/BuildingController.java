package com.brainstem.myestate.controllers;

import com.brainstem.myestate.payload.BuildingDto;
import com.brainstem.myestate.service.BuildingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BuildingController {
        private BuildingService buildingService;

        public BuildingController(BuildingService buildingService) {
            this.buildingService = buildingService;
        }

        //create building post rest api
        @PostMapping("/v1/buildings")
        public ResponseEntity<BuildingDto> createBuilding(
                @RequestBody BuildingDto buildingDto
        ){
            return new ResponseEntity<>(buildingService.createBuilding(buildingDto), HttpStatus.CREATED);
        }

        //get all buildings rest api
        @GetMapping("/v1/buildings")
        public List<BuildingDto> getAllBuildings(
                @RequestParam(name = "pageNumber", defaultValue = "0", required = false) int pageNumber,
                @RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize
        ){
            return buildingService.getAllBuildings(pageNumber, pageSize);
        }

        //get building by id
        @GetMapping("/v1/buildings/{id}")
        public ResponseEntity<BuildingDto> getBuildingByid(
                @PathVariable(name = "id") long id){
            return ResponseEntity.ok(buildingService.getBuildingById(id));
        }

        //update building by id
        @PutMapping("/v1/buildings/{id}")
        public ResponseEntity<BuildingDto> updateBuilding(
                @PathVariable(name = "id") long id, @RequestBody BuildingDto buildingDto
        ){
            BuildingDto updatedBuildingResponse =  buildingService.updateBuilding(id, buildingDto);
            return new ResponseEntity<>(updatedBuildingResponse, HttpStatus.OK);
        }

        //delete apartment rest api
        @DeleteMapping("/v1/buildings/{id}")
        public ResponseEntity<String> deleteApartment(@PathVariable(name = "id") long id){
            buildingService.deleteBuilding(id);
            return new ResponseEntity<>("Building entity is deleted succesfully!", HttpStatus.OK);
        }
}
