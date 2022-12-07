package com.brainstem.myestate.service.serviceImpl;

import com.brainstem.myestate.exception.ResourceNotFoundException;
import com.brainstem.myestate.model.Apartment;
import com.brainstem.myestate.payload.ApartmentDto;
import com.brainstem.myestate.repository.ApartmentRepository;
import com.brainstem.myestate.repository.UserRepository;
import com.brainstem.myestate.service.ApartmentService;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApartmentServiceImpl implements ApartmentService {
    private ApartmentRepository apartmentRepository;
    private UserRepository userRepository;

    public ApartmentServiceImpl(
            ApartmentRepository apartmentRepository,
            UserRepository userRepository)
    {
        this.apartmentRepository = apartmentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ApartmentDto createApartment(ApartmentDto apartmentDto) {
        Apartment apartment = mapToEntity(apartmentDto);
        Apartment newApartment = apartmentRepository.save(apartment);
        ApartmentDto apartmentResponse = mapToDto(newApartment);
        return apartmentResponse;
    }

    @Override
    public List<ApartmentDto> getAllApartments(int pageNumber, int pageSize) {
        // create pageRequest instance
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        Page<Apartment> apartments = apartmentRepository.findAll(pageRequest);

        //get content from page object
        List<Apartment> listOfApartments = apartments.getContent();
        return listOfApartments.stream().map(apartment -> mapToDto(apartment)).collect(Collectors.toList());
    }

    @Override
    public ApartmentDto getApartmentById(long id) {
        Apartment apartment =  apartmentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Apartment", "id", id));
        return mapToDto(apartment);
    }

    @Override
    public void deleteApartment(long id) {
        //get apartment by id from the db
        Apartment apartment = apartmentRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Apartment", "id", id)
        );
        apartmentRepository.delete(apartment);
    }

    @Override
    public ApartmentDto updateApartment(long id, ApartmentDto apartmentDto) {
        //get apartment by id or throw exception if it does not exist
        Apartment apartment = apartmentRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Apartment", "id", id)
        );

        //update the retrieve entity from db
        apartment.setOwner(apartmentDto.getOwner());
        apartment.setNoOfRooms(apartmentDto.getNoOfRooms());
        apartment.setNoOfSittingRooms(apartment.getNoOfSittingRooms());
        apartment.setNoOfToilets(apartmentDto.getNoOfToilets());
        apartment.setNoOfKitchens(apartmentDto.getNoOfKitchens());
        apartment.setNoOfWardropes(apartmentDto.getNoOfWardropes());
        apartment.setNoOfParkingSpace(apartmentDto.getNoOfParkingSpace());
        apartment.setEstateResidence(apartmentDto.isEstateResidence());
        apartment.setHoursOfElectricity(apartmentDto.getHoursOfElectricity());
        apartment.setHoursOfWaterSupply(apartmentDto.getHoursOfWaterSupply());
        apartment.setServiced(apartmentDto.isServiced());
        apartment.setAmount(apartmentDto.getAmount());
        apartment.setForRent(apartmentDto.getForRent());
        apartment.setAgencyFee(apartmentDto.getAgencyFee());
        apartment.setCommision(apartmentDto.getCommision());
        apartment.setOtherFee(apartmentDto.getOtherFee());
        apartment.setInfo(apartmentDto.getInfo());
        apartment.setUser(apartmentDto.getUser());
        apartment.setBackImage(apartmentDto.getBackImage());
        apartment.setFrontImage(apartmentDto.getFrontImage());
        apartment.setSittingRoomImage(apartmentDto.getSittingRoomImage());
        apartment.setBedroom1Image(apartmentDto.getBedroom1Image());
        apartment.setBedroom2Image(apartmentDto.getBedroom2Image());
        apartment.setKitchenImage(apartmentDto.getKitchenImage());
        apartment.setToiletImage(apartmentDto.getToiletImage());
        apartment.setApartmentType(apartmentDto.getApartmentType());
        apartment.setAddress(apartmentDto.getAddress());
        apartment.setBuildingType(apartmentDto.getBuildingType());

        //save to db
        Apartment updatedApartment = apartmentRepository.save(apartment);
        return mapToDto(updatedApartment);
    }


    //convert dto (client) to entity (db)
    private Apartment mapToEntity(ApartmentDto apartmentDto) {
        Apartment apartment = new Apartment();

        apartment.setOwner(apartmentDto.getOwner());
        apartment.setNoOfRooms(apartmentDto.getNoOfRooms());
        apartment.setNoOfSittingRooms(apartmentDto.getNoOfSittingRooms());

        LoggerFactory.getLogger(" Dto ").info("Dto to Db -> To Client Apartment Dto" + apartmentDto.getNoOfSittingRooms());
        LoggerFactory.getLogger("Apartment Entity").info("Db to Dto -> From Db Apartment Entity" + apartment.getNoOfSittingRooms());

        apartment.setNoOfToilets(apartmentDto.getNoOfToilets());
        apartment.setNoOfKitchens(apartmentDto.getNoOfKitchens());
        apartment.setNoOfWardropes(apartmentDto.getNoOfWardropes());
        apartment.setNoOfParkingSpace(apartmentDto.getNoOfParkingSpace());
        apartment.setEstateResidence(apartmentDto.isEstateResidence());
        apartment.setHoursOfElectricity(apartmentDto.getHoursOfElectricity());
        apartment.setHoursOfWaterSupply(apartmentDto.getHoursOfWaterSupply());
        apartment.setServiced(apartmentDto.isServiced());
        apartment.setAmount(apartmentDto.getAmount());
        apartment.setForRent(apartmentDto.getForRent());
        apartment.setAgencyFee(apartmentDto.getAgencyFee());
        apartment.setCommision(apartmentDto.getCommision());
        apartment.setOtherFee(apartmentDto.getOtherFee());
        apartment.setInfo(apartmentDto.getInfo());
        apartment.setUser(apartmentDto.getUser());
        apartment.setApartmentType(apartmentDto.getApartmentType());
        apartment.setAddress(apartmentDto.getAddress());
        apartment.setBuildingType(apartmentDto.getBuildingType());

        return apartment;
    }


    //convert  to entity (db) dto (client)
    private ApartmentDto mapToDto(Apartment apartment) {
        ApartmentDto apartmentDto = new ApartmentDto();

        apartmentDto.setId(apartment.getId());
        apartmentDto.setOwner(apartment.getOwner());
        apartmentDto.setNoOfRooms(apartment.getNoOfRooms());
        apartmentDto.setNoOfSittingRooms(apartment.getNoOfSittingRooms());
        LoggerFactory.getLogger("Apartment Dto").info("To Client Apartment Dto" + apartmentDto.getNoOfSittingRooms());
        LoggerFactory.getLogger("Apartment Entity").info("From Db Apartment Entity" + apartment.getNoOfSittingRooms());
        apartmentDto.setNoOfToilets(apartment.getNoOfToilets());
        apartmentDto.setNoOfKitchens(apartment.getNoOfKitchens());
        apartmentDto.setNoOfWardropes(apartment.getNoOfWardropes());
        apartmentDto.setNoOfParkingSpace(apartment.getNoOfParkingSpace());
        apartmentDto.setEstateResidence(apartment.isEstateResidence());
        apartmentDto.setHoursOfElectricity(apartment.getHoursOfElectricity());
        apartmentDto.setHoursOfWaterSupply(apartment.getHoursOfWaterSupply());
        apartmentDto.setServiced(apartment.isServiced());
        apartmentDto.setAmount(apartment.getAmount());
        apartmentDto.setForRent(apartment.getForRent());
        apartmentDto.setAgencyFee(apartment.getAgencyFee());
        apartmentDto.setCommision(apartment.getCommision());
        apartmentDto.setOtherFee(apartment.getOtherFee());
        apartmentDto.setInfo(apartment.getInfo());
        apartmentDto.setUser(apartment.getUser());
        apartmentDto.setBackImage(apartment.getBackImage());
        apartmentDto.setFrontImage(apartment.getFrontImage());
        apartmentDto.setSittingRoomImage(apartment.getSittingRoomImage());
        apartmentDto.setBedroom1Image(apartment.getBedroom1Image());
        apartmentDto.setBedroom2Image(apartment.getBedroom2Image());
        apartmentDto.setKitchenImage(apartment.getKitchenImage());
        apartmentDto.setToiletImage(apartment.getToiletImage());
        apartmentDto.setApartmentType(apartment.getApartmentType());
        apartmentDto.setAddress(apartment.getAddress());
        apartmentDto.setBuildingType(apartment.getBuildingType());

        return apartmentDto;
    }
}
