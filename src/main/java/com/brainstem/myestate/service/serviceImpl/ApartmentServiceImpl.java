package com.brainstem.myestate.service.serviceImpl;

import com.brainstem.myestate.dto.response.ApartmentDtoResponse;
import com.brainstem.myestate.exception.ResourceNotFoundException;
import com.brainstem.myestate.model.Apartment;
import com.brainstem.myestate.model.Image;
import com.brainstem.myestate.dto.request.ApartmentDto;
import com.brainstem.myestate.dto.response.ImageResponse;
import com.brainstem.myestate.repository.ApartmentRepository;
import com.brainstem.myestate.repository.ImageRepository;
import com.brainstem.myestate.repository.UserRepository;
import com.brainstem.myestate.service.ApartmentService;
import com.brainstem.myestate.service.ImagesService;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class ApartmentServiceImpl implements ApartmentService {
    private final ApartmentRepository apartmentRepository;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;
    private  final  ImagesService imagesService;

    Logger log = Logger.getLogger(this.getClass().getCanonicalName());

    public ApartmentServiceImpl(
            ApartmentRepository apartmentRepository,
            UserRepository userRepository,
            ImageRepository imageRepository,
            ImagesService imagesService)
    {
        this.apartmentRepository = apartmentRepository;
        this.userRepository = userRepository;
        this.imageRepository = imageRepository;
        this.imagesService = imagesService;
    }


    @Override
    public ApartmentDtoResponse createApartment(ApartmentDto apartmentInputObject) throws Exception {
        List<Image> listOfApartmentImages = imagesService.saveImages(apartmentInputObject.getFiles());
        Apartment apartment = mapToEntity(apartmentInputObject);
        apartment.setApartmentImages(listOfApartmentImages);
        Apartment newApartment = apartmentRepository.save(apartment);
        log.info("MAP TO APARTMENT ENTITY" + apartment.getId());
        return mapToDto(newApartment);
    }

    @Override
    public List<ApartmentDtoResponse> getAllApartments(int pageNumber, int pageSize) {
        // create pageRequest instance
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        Page<Apartment> apartments = apartmentRepository.findAll(pageRequest);

        //get content from page object
        List<Apartment> listOfApartments = apartments.getContent();
        return listOfApartments.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public ApartmentDtoResponse getApartmentById(long id) {
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
    public List<ApartmentDtoResponse> searchApartments(String query) {
        List<Apartment> listOfApartments = apartmentRepository.searchApartments(query);
        return listOfApartments.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public ApartmentDtoResponse updateApartment(long id, ApartmentDto apartmentInputObject) {
        //get apartment by id or throw exception if it does not exist
        Apartment apartment = apartmentRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Apartment", "id", id)
        );

        apartment.setOwner(apartmentInputObject.getOwner());
        apartment.setNoOfRooms(apartmentInputObject.getNoOfRooms());
        apartment.setNoOfSittingRooms(apartmentInputObject.getNoOfSittingRooms());
        apartment.setNoOfToilets(apartmentInputObject.getNoOfToilets());
        apartment.setNoOfKitchens(apartmentInputObject.getNoOfKitchens());
        apartment.setNoOfWardropes(apartmentInputObject.getNoOfWardropes());
        apartment.setNoOfParkingSpace(apartmentInputObject.getNoOfParkingSpace());
        apartment.setEstateResidence(apartmentInputObject.isEstateResidence());
        apartment.setHoursOfElectricity(apartmentInputObject.getHoursOfElectricity());
        apartment.setHoursOfWaterSupply(apartmentInputObject.getHoursOfWaterSupply());
        apartment.setServiced(apartmentInputObject.isServiced());
        apartment.setAmount(apartmentInputObject.getAmount());
        apartment.setServiced(apartmentInputObject.isServiced());
        apartment.setAgencyFee(apartmentInputObject.getAgencyFee());
        apartment.setCommision(apartmentInputObject.getCommision());
        apartment.setOtherFee(apartmentInputObject.getOtherFee());
        apartment.setInfo(apartmentInputObject.getInfo());
        apartment.setApartmentType(apartmentInputObject.getApartmentType());
        apartment.setAddress(apartmentInputObject.getAddress());
        apartment.setBuildingType(apartmentInputObject.getBuildingType());

        return mapToDto(apartment);
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
        apartment.setStatus(apartmentDto.getStatus());
        apartment.setAgencyFee(apartmentDto.getAgencyFee());
        apartment.setCommision(apartmentDto.getCommision());
        apartment.setOtherFee(apartmentDto.getOtherFee());
        apartment.setInfo(apartmentDto.getInfo().toLowerCase(Locale.ROOT));
        apartment.setApartmentType(apartmentDto.getApartmentType());
        apartment.setAddress(apartmentDto.getAddress());
        apartment.setBuildingType(apartmentDto.getBuildingType());

        return apartment;
    }


    //convert  to entity (db) dto (client)
    private ApartmentDtoResponse mapToDto(Apartment apartment) {
        ArrayList<ImageResponse> listOfImageResponse = new ArrayList<>();
        ApartmentDtoResponse apartmentDtoResponse = new ApartmentDtoResponse();

        apartmentDtoResponse.setId(apartment.getId());
        apartmentDtoResponse.setOwner(apartment.getOwner());
        apartmentDtoResponse.setNoOfRooms(apartment.getNoOfRooms());
        apartmentDtoResponse.setNoOfSittingRooms(apartment.getNoOfSittingRooms());
//        LoggerFactory.getLogger("Apartment Dto").info("To Client Apartment Dto" + apartmentDto.getNoOfSittingRooms());
//        LoggerFactory.getLogger("Apartment Entity").info("From Db Apartment Entity" + apartment.getNoOfSittingRooms());
        apartmentDtoResponse.setNoOfToilets(apartment.getNoOfToilets());
        apartmentDtoResponse.setNoOfKitchens(apartment.getNoOfKitchens());
        apartmentDtoResponse.setNoOfWardropes(apartment.getNoOfWardropes());
        apartmentDtoResponse.setNoOfParkingSpace(apartment.getNoOfParkingSpace());
        apartmentDtoResponse.setEstateResidence(apartment.isEstateResidence());
        apartmentDtoResponse.setHoursOfElectricity(apartment.getHoursOfElectricity());
        apartmentDtoResponse.setHoursOfWaterSupply(apartment.getHoursOfWaterSupply());
        apartmentDtoResponse.setServiced(apartment.isServiced());
        apartmentDtoResponse.setAmount(apartment.getAmount());
        apartmentDtoResponse.setStatus(apartment.getStatus());
        apartmentDtoResponse.setActive(apartment.isActive());
        apartmentDtoResponse.setAgencyFee(apartment.getAgencyFee());
        apartmentDtoResponse.setCommision(apartment.getCommision());
        apartmentDtoResponse.setOtherFee(apartment.getOtherFee());
        apartmentDtoResponse.setInfo(apartment.getInfo().toLowerCase(Locale.ROOT));

        List<Image> images = apartment.getApartmentImages();
        images.forEach((image)->{
            ImageResponse imageResponse = new ImageResponse(image.getFileName(),  image.getUrl(), image.getFileType(), image.getSize());
            listOfImageResponse.add(imageResponse);
        });
        apartmentDtoResponse.setImages(listOfImageResponse);
        apartmentDtoResponse.setApartmentType(apartment.getApartmentType());
        apartmentDtoResponse.setAddress(apartment.getAddress());
        apartmentDtoResponse.setBuildingType(apartment.getBuildingType());
        return apartmentDtoResponse;
    }

}
