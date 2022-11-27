package com.brainstem.myestate.service.serviceImpl;

import com.brainstem.myestate.exception.ResourceNotFoundException;
import com.brainstem.myestate.model.Land;
import com.brainstem.myestate.payload.LandDto;
import com.brainstem.myestate.repository.LandRepository;
import com.brainstem.myestate.repository.UserRepository;
import com.brainstem.myestate.service.LandService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LandServiceImpl implements LandService {
    UserRepository userRepository;
    LandRepository landRepository;

    public LandServiceImpl(UserRepository userRepository, LandRepository landRepository) {
        this.userRepository = userRepository;
        this.landRepository = landRepository;
    }


    @Override
    public LandDto createLand(LandDto landDto) {
        Land land = mapToEntity(landDto);
        Land newLand = landRepository.save(land);
        return mapToDto(newLand);
    }

    @Override
    public List<LandDto> getAllLands(int pageNumber, int pageSize) {
        // create pageRequest instance
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        Page<Land> lands = landRepository.findAll(pageRequest);

        //get content from page object
        List<Land> listOfLands = lands.getContent();
        return listOfLands.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public LandDto getLandById(long id) {
        Land land =  landRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Land", "id", id));
        return mapToDto(land);
    }

    @Override
    public LandDto updateLand(long id, LandDto landDto) {
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
    private Land mapToEntity(LandDto landDto){
        return Land.builder()
                .id(landDto.getId())
                .address(landDto.getAddress())
                .agencyFee(landDto.getAgencyFee())
                .amount(landDto.getAmount())
                .cOfO(landDto.getCOfO())
                .info(landDto.getInfo())
                .measure(landDto.getMeasure())
                .forRent(landDto.isForRent())
                .owner(landDto.getOwner())
                .estateLand(landDto.getEstateLand())
                .commision(landDto.getCommision())
                .noOfPlots(landDto.getNoOfPlots())
                .receipt(landDto.getReceipt())
                .otherFee(landDto.getOtherFee())
                .survey(landDto.getSurvey())
                .user(landDto.getUser())
                .address(landDto.getAddress())
                .forRent(landDto.isForRent())
                .frontImage(landDto.getFrontImage())
                .backImage(landDto.getBackImage())
                .sittingRoomImage(landDto.getSittingRoomImage())
                .bedroom1Image(landDto.getBedroom1Image())
                .bedroom2Image(landDto.getBedroom2Image())
                .toiletImage(landDto.getToiletImage())
                .build();
    }

    //mapToDto
    private LandDto mapToDto(Land land){
        return LandDto.builder()
                .id(land.getId())
                .address(land.getAddress())
                .agencyFee(land.getAgencyFee())
                .amount(land.getAmount())
                .cOfO(land.getCOfO())
                .info(land.getInfo())
                .measure(land.getMeasure())
                .forRent(land.isForRent())
                .owner(land.getOwner())
                .estateLand(land.getEstateLand())
                .commision(land.getCommision())
                .noOfPlots(land.getNoOfPlots())
                .receipt(land.getReceipt())
                .otherFee(land.getOtherFee())
                .survey(land.getSurvey())
                .user(land.getUser())
                .address(land.getAddress())
                .forRent(land.isForRent())
                .frontImage(land.getFrontImage())
                .backImage(land.getBackImage())
                .sittingRoomImage(land.getSittingRoomImage())
                .bedroom1Image(land.getBedroom1Image())
                .bedroom2Image(land.getBedroom2Image())
                .toiletImage(land.getToiletImage())
                .build();
    }
}
