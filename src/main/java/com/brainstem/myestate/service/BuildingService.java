package com.brainstem.myestate.service;

import com.brainstem.myestate.dto.request.BuildingDto;
import com.brainstem.myestate.dto.response.BuildingDtoResponse;

import java.util.List;

public interface BuildingService {
    BuildingDtoResponse createBuilding(BuildingDto buildingDto) throws Exception;
    List<BuildingDtoResponse> getAllBuildings(int pageNumber, int pageSize);
    BuildingDtoResponse getBuildingById(long id);
    BuildingDtoResponse updateBuilding(long id, BuildingDto buildingDto) throws Exception;
    void deleteBuilding(long id);

}
