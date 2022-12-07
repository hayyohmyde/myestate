package com.brainstem.myestate.service;

import com.brainstem.myestate.payload.BuildingDto;

import java.util.List;

public interface BuildingService {
    BuildingDto createBuilding(BuildingDto buildingDto);
    List<BuildingDto> getAllBuildings(int pageNumber, int pageSize);
    BuildingDto getBuildingById(long id);
    BuildingDto updateBuilding(long id, BuildingDto buildingDto);
    void deleteBuilding(long id);

}
