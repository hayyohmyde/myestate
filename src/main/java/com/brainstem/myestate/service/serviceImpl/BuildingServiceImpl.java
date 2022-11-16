package com.brainstem.myestate.service.serviceImpl;

import com.brainstem.myestate.exception.ResourceNotFoundException;
import com.brainstem.myestate.model.Building;
import com.brainstem.myestate.payload.BuildingDto;
import com.brainstem.myestate.repository.BuildingRepository;
import com.brainstem.myestate.service.BuildingService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuildingServiceImpl implements BuildingService {

    private BuildingRepository buildingRepository;

    public BuildingServiceImpl(BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
    }


        @Override
        public BuildingDto createBuilding(BuildingDto buildingDto) {
            Building building = mapToEntity(buildingDto);
            Building newBuilding = buildingRepository.save(building);
            return mapToDto(newBuilding);
        }

        @Override
        public List<BuildingDto> getAllBuildings(int pageNumber, int pageSize) {
            // create pageRequest instance
            PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
            Page<Building> buildings = buildingRepository.findAll(pageRequest);

            //get content from page object
            List<Building> listOfBuildings = buildings.getContent();
            return listOfBuildings.stream().map(this::mapToDto).collect(Collectors.toList());
        }

        @Override
        public BuildingDto getBuildingById(long id) {
            Building building =  buildingRepository.findById(id).orElseThrow(
                    () -> new ResourceNotFoundException("Land", "id", id));
            return mapToDto(building);
        }

        @Override
        public BuildingDto updateBuilding(long id, BuildingDto buildingDto) {
            //get building by id or throw exception if it does not exist
            Building building = buildingRepository.findById(id).orElseThrow(
                    ()-> new ResourceNotFoundException("Building", "id", id)
            );

            //convert to entity and save to db
            Building updatedHouse = buildingRepository.save(mapToEntity(buildingDto));
            return mapToDto(updatedHouse);
        }

        @Override
        public void deleteBuilding(long id) {
            //get apartment by id from the db
            Building building = buildingRepository.findById(id).orElseThrow(
                    ()-> new ResourceNotFoundException("Apartment", "id", id)
            );
            buildingRepository.delete(building);
        }

        //mapToEntity
        private Building mapToEntity(BuildingDto buildingDto){
            return Building.builder()
                    .id(buildingDto.getId())
                    .address(buildingDto.getAddress())
                    .agencyFee(buildingDto.getAgencyFee())
                    .owner(buildingDto.getOwner())
                    .forRent(buildingDto.getForRent())
                    .receipt(buildingDto.isReceipt())
                    .info(buildingDto.getInfo())
                    .surveyDocument(buildingDto.isSurveyDocument())
                    .landMeasure(buildingDto.isLandMeasure())
                    .cOfO(buildingDto.isCOfO())
                    .buildingType(buildingDto.getBuildingType())
                    .noOfApartments(buildingDto.getNoOfApartments())
                    .apartmentType(buildingDto.getApartmentType())
                    .designPlan(buildingDto.isDesignPlan())
                    .otherFee(buildingDto.getOtherFee())
                    .otherRelevantDocument(buildingDto.isOtherRelevantDocument())
                    .amount(buildingDto.getAmount())
                    .commision(buildingDto.getCommision())
                    .user(buildingDto.getUser())
                    .build();
        }

        //mapToDto
        private BuildingDto mapToDto(Building building){
            return BuildingDto.builder()
                    .id(building.getId())
                    .address(building.getAddress())
                    .agencyFee(building.getAgencyFee())
                    .owner(building.getOwner())
                    .forRent(building.getForRent())
                    .receipt(building.isReceipt())
                    .info(building.getInfo())
                    .surveyDocument(building.isSurveyDocument())
                    .landMeasure(building.isLandMeasure())
                    .cOfO(building.isCOfO())
                    .buildingType(building.getBuildingType())
                    .noOfApartments(building.getNoOfApartments())
                    .apartmentType(building.getApartmentType())
                    .designPlan(building.isDesignPlan())
                    .otherFee(building.getOtherFee())
                    .otherRelevantDocument(building.isOtherRelevantDocument())
                    .amount(building.getAmount())
                    .commision(building.getCommision())
                    .user(building.getUser())
                    .build();
        }
}

