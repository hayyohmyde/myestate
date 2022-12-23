package com.brainstem.myestate.service.serviceImpl;

import com.brainstem.myestate.dto.response.BuildingDtoResponse;
import com.brainstem.myestate.dto.response.ImageResponse;
import com.brainstem.myestate.exception.ResourceNotFoundException;
import com.brainstem.myestate.model.Building;
import com.brainstem.myestate.dto.request.BuildingDto;
import com.brainstem.myestate.repository.BuildingRepository;
import com.brainstem.myestate.service.BuildingService;
import com.brainstem.myestate.service.ImagesService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuildingServiceImpl implements BuildingService {

    private BuildingRepository buildingRepository;
    private ImagesService imagesService;

    public BuildingServiceImpl(BuildingRepository buildingRepository, ImagesService imagesService) {
        this.buildingRepository = buildingRepository;
        this.imagesService = imagesService;
    }


        @Override
        public BuildingDtoResponse createBuilding(BuildingDto buildingDto) throws Exception {
            Building building = mapToEntity(buildingDto);
            Building newBuilding = buildingRepository.save(building);
            return mapToDto(newBuilding);
        }

        @Override
        public List<BuildingDtoResponse> getAllBuildings(int pageNumber, int pageSize) {
            // create pageRequest instance
            PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
            Page<Building> buildings = buildingRepository.findAll(pageRequest);

            //get content from page object
            List<Building> listOfBuildings = buildings.getContent();
            return listOfBuildings.stream().map(this::mapToDto).collect(Collectors.toList());
        }

        @Override
        public BuildingDtoResponse getBuildingById(long id) {
            Building building =  buildingRepository.findById(id).orElseThrow(
                    () -> new ResourceNotFoundException("Land", "id", id));
            return mapToDto(building);
        }

        @Override
        public BuildingDtoResponse updateBuilding(long id, BuildingDto buildingDto) throws Exception {
            //get building by id or throw exception if it does not exist
            buildingRepository.findById(id).orElseThrow(
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
        private Building mapToEntity(BuildingDto buildingDto) throws Exception {
            return Building.builder()
                    .address(buildingDto.getAddress())
                    .agencyFee(buildingDto.getAgencyFee())
                    .owner(buildingDto.getOwner())
                    .status( buildingDto.getStatus())
                    .info(buildingDto.getInfo())
                    .document( buildingDto.getDocument())
                    .landMeasurement(buildingDto.getLandMeasurement())
                    .document(buildingDto.getDocument())
                    .buildingType(buildingDto.getBuildingType())
                    .noOfApartments(buildingDto.getNoOfApartments())
                    .apartmentType(buildingDto.getApartmentType())
                    .otherFee(buildingDto.getOtherFee())
                    .amount(buildingDto.getAmount())
                    .commision(buildingDto.getCommision())
                    .images(imagesService.saveImages(buildingDto.getFiles()))
                    .build();
        }

        //mapToDto
        private BuildingDtoResponse mapToDto(Building building){
        List<ImageResponse> imageResponse = building.getImages().stream().map(
                (image)-> new ImageResponse(image.getFileName(), image.getUrl(), image.getFileType(), image.getSize())
        ).collect(Collectors.toList());
            return BuildingDtoResponse.builder()
                    .id(building.getId())
                    .address(building.getAddress())
                    .agencyFee(building.getAgencyFee())
                    .owner(building.getOwner())
                    .status(building.getStatus())
                    .info(building.getInfo())
                    .landMeasurement(building.getLandMeasurement())
                    .document(building.getDocument())
                    .buildingType(building.getBuildingType())
                    .noOfApartments(building.getNoOfApartments())
                    .apartmentType(building.getApartmentType())
                    .otherFee(building.getOtherFee())
                    .amount(building.getAmount())
                    .commision(building.getCommision())
                    .images(imageResponse)
                    .build();
        }


}

