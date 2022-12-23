package com.brainstem.myestate.service.serviceImpl;

import com.brainstem.myestate.dto.response.ImageResponse;
import com.brainstem.myestate.dto.response.LandDtoResponse;
import com.brainstem.myestate.exception.ResourceNotFoundException;
import com.brainstem.myestate.model.Image;
import com.brainstem.myestate.model.Land;
import com.brainstem.myestate.dto.request.LandDto;
import com.brainstem.myestate.repository.ImageRepository;
import com.brainstem.myestate.repository.LandRepository;
import com.brainstem.myestate.repository.UserRepository;
import com.brainstem.myestate.service.ImagesService;
import com.brainstem.myestate.service.LandService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class LandServiceImpl implements LandService {
    UserRepository userRepository;
    LandRepository landRepository;
    ImagesService imagesService;

    public LandServiceImpl(UserRepository userRepository,
                           LandRepository landRepository,
                           ImagesService imageService) {
        this.userRepository = userRepository;
        this.landRepository = landRepository;
        this.imagesService = imageService;
    }


    @Override
    public LandDtoResponse createLand(LandDto landDto) throws Exception {
        Land land = mapToEntity(landDto);
        Land newLand = landRepository.save(land);
        return mapToDto(newLand);
    }

    @Override
    public List<LandDtoResponse> getAllLands(int pageNumber, int pageSize) {
        // create pageRequest instance
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        Page<Land> lands = landRepository.findAll(pageRequest);

        //get content from page object
        List<Land> listOfLands = lands.getContent();
        return listOfLands.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public LandDtoResponse getLandById(long id) {
        Land land =  landRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Land", "id", id));
        return mapToDto(land);
    }

    @Override
    public LandDtoResponse updateLand(long id, LandDto landDto) throws Exception {
        //get land by id or throw exception if it does not exist
        Land land = landRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Land", "id", id)
        );

        //convert to entity and save to db
        Land updatedLand = landRepository.save(mapToEntity(landDto));
        return mapToDto(updatedLand);
    }

    @Override
    public void deleteLand(long id) {
        //get apartment by id from the db
        Land land = landRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Apartment", "id", id)
        );
        landRepository.delete(land);
    }

    //mapToEntity
    private Land mapToEntity(LandDto landDto) throws Exception {
        return Land.builder()
                .address(landDto.getAddress())
                .agencyFee(landDto.getAgencyFee())
                .amount(landDto.getAmount())
                .document(landDto.getDocument())
                .info(landDto.getInfo())
                .measurement(landDto.getMeasurement())
                .status(landDto.getStatus())
                .owner(landDto.getOwner())
                .isEstateLand(landDto.getIsEstateLand())
                .commision(landDto.getCommision())
                .noOfPieces(landDto.getNoOfPieces())
                .otherFee(landDto.getOtherFee())
                .address(landDto.getAddress())
                .landImages(imagesService.saveImages(landDto.getFiles()))
                .build();
    }

    //mapToDto
    private LandDtoResponse mapToDto(Land land){
        List<Image> images = land.getLandImages();
        List<ImageResponse> imageResponses = images.stream().map(
                (image -> new ImageResponse(
                        image.getFileName(),
                        image.getUrl(),
                        image.getFileType(),
                        image.getSize()))
        ).collect(Collectors.toList());

        return LandDtoResponse.builder()
                .id(land.getId())
                .address(land.getAddress())
                .agencyFee(land.getAgencyFee())
                .amount(land.getAmount())
                .document(land.getDocument())
                .info(land.getInfo())
                .measurement(land.getMeasurement())
                .status(land.getStatus())
                .owner(land.getOwner())
                .isEstateLand(land.getIsEstateLand())
                .commision(land.getCommision())
                .noOfPieces(land.getNoOfPieces())
                .otherFee(land.getOtherFee())
                .address(land.getAddress())
                .status(land.getStatus())
                .landImages(imageResponses)
                .build();
    }
}
