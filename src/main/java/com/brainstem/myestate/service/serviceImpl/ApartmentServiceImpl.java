package com.brainstem.myestate.service.serviceImpl;

import com.brainstem.myestate.exception.ResourceNotFoundException;
import com.brainstem.myestate.model.Apartment;
import com.brainstem.myestate.model.Image;
import com.brainstem.myestate.payload.ApartmentDto;
import com.brainstem.myestate.payload.ApartmentInputObject;
import com.brainstem.myestate.payload.ImageResponse;
import com.brainstem.myestate.repository.ApartmentRepository;
import com.brainstem.myestate.repository.ImageRepository;
import com.brainstem.myestate.repository.UserRepository;
import com.brainstem.myestate.service.ApartmentService;
import com.brainstem.myestate.service.ImagesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApartmentServiceImpl implements ApartmentService {
    private final ApartmentRepository apartmentRepository;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;
    private  final  ImagesService imagesService;

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
    public ApartmentDto createApartment(ApartmentInputObject apartmentInputObject) throws Exception {
//        LoggerFactory.getLogger("Apartment Input Object").info(apartmentInputObject.toString());
        List<Image> listOfApartmentImages = imagesService.saveImages(apartmentInputObject.getFiles());
        Apartment apartment = mapToEntity(apartmentInputObject);
        apartment.setImages(listOfApartmentImages);
        Apartment newApartment = apartmentRepository.save(apartment);
        return mapToDto(newApartment);
    }

    @Override
    public List<ApartmentDto> getAllApartments(int pageNumber, int pageSize) {
        // create pageRequest instance
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        Page<Apartment> apartments = apartmentRepository.findAll(pageRequest);

        //get content from page object
        List<Apartment> listOfApartments = apartments.getContent();
        return listOfApartments.stream().map(this::mapToDto).collect(Collectors.toList());
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
    public List<ApartmentDto> searchApartments(String query) {
        List<Apartment> listOfApartments = apartmentRepository.searchApartments(query);
        return listOfApartments.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public ApartmentDto updateApartment(long id, ApartmentInputObject apartmentInputObject) {
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
//        apartment.setUser(apartmentInputObject.getUser());
//        apartment.setImages(apartmentInputObject.getFiles());
        apartment.setApartmentType(apartmentInputObject.getApartmentType());
        apartment.setAddress(apartmentInputObject.getAddress());
        apartment.setBuildingType(apartmentInputObject.getBuildingType());

        return mapToDto(apartment);
    }


    //convert dto (client) to entity (db)
    private Apartment mapToEntity(ApartmentInputObject apartmentInputObject) {
        Apartment apartment = new Apartment();

        apartment.setOwner(apartmentInputObject.getOwner());
        apartment.setNoOfRooms(apartmentInputObject.getNoOfRooms());
        apartment.setNoOfSittingRooms(apartmentInputObject.getNoOfSittingRooms());

        LoggerFactory.getLogger(" Dto ").info("Dto to Db -> To Client Apartment Dto" + apartmentInputObject.getNoOfSittingRooms());
        LoggerFactory.getLogger("Apartment Entity").info("Db to Dto -> From Db Apartment Entity" + apartment.getNoOfSittingRooms());

        apartment.setNoOfToilets(apartmentInputObject.getNoOfToilets());
        apartment.setNoOfKitchens(apartmentInputObject.getNoOfKitchens());
        apartment.setNoOfWardropes(apartmentInputObject.getNoOfWardropes());
        apartment.setNoOfParkingSpace(apartmentInputObject.getNoOfParkingSpace());
        apartment.setEstateResidence(apartmentInputObject.isEstateResidence());
        apartment.setHoursOfElectricity(apartmentInputObject.getHoursOfElectricity());
        apartment.setHoursOfWaterSupply(apartmentInputObject.getHoursOfWaterSupply());
        apartment.setServiced(apartmentInputObject.isServiced());
        apartment.setAmount(apartmentInputObject.getAmount());
        apartment.setRent(apartmentInputObject.isRent());
        apartment.setAgencyFee(apartmentInputObject.getAgencyFee());
        apartment.setCommision(apartmentInputObject.getCommision());
        apartment.setOtherFee(apartmentInputObject.getOtherFee());
        apartment.setInfo(apartmentInputObject.getInfo());
//        apartment.setUser(apartmentDto.getUser());
        apartment.setApartmentType(apartmentInputObject.getApartmentType());
        apartment.setAddress(apartmentInputObject.getAddress());
        apartment.setBuildingType(apartmentInputObject.getBuildingType());

        return apartment;
    }


    //convert  to entity (db) dto (client)
    private ApartmentDto mapToDto(Apartment apartment) {
        ArrayList<ImageResponse> listOfImageResponse = new ArrayList<>();
        ApartmentDto apartmentDto = new ApartmentDto();

        apartmentDto.setId(apartment.getId());
        apartmentDto.setOwner(apartment.getOwner());
        apartmentDto.setNoOfRooms(apartment.getNoOfRooms());
        apartmentDto.setNoOfSittingRooms(apartment.getNoOfSittingRooms());
//        LoggerFactory.getLogger("Apartment Dto").info("To Client Apartment Dto" + apartmentDto.getNoOfSittingRooms());
//        LoggerFactory.getLogger("Apartment Entity").info("From Db Apartment Entity" + apartment.getNoOfSittingRooms());
        apartmentDto.setNoOfToilets(apartment.getNoOfToilets());
        apartmentDto.setNoOfKitchens(apartment.getNoOfKitchens());
        apartmentDto.setNoOfWardropes(apartment.getNoOfWardropes());
        apartmentDto.setNoOfParkingSpace(apartment.getNoOfParkingSpace());
        apartmentDto.setEstateResidence(apartment.isEstateResidence());
        apartmentDto.setHoursOfElectricity(apartment.getHoursOfElectricity());
        apartmentDto.setHoursOfWaterSupply(apartment.getHoursOfWaterSupply());
        apartmentDto.setServiced(apartment.isServiced());
        apartmentDto.setAmount(apartment.getAmount());
        apartmentDto.setRent(apartment.isRent());
        apartmentDto.setActive(apartmentDto.isActive());
        apartmentDto.setAgencyFee(apartment.getAgencyFee());
        apartmentDto.setCommision(apartment.getCommision());
        apartmentDto.setOtherFee(apartment.getOtherFee());
        apartmentDto.setInfo(apartment.getInfo());
//        apartmentDto.setUser(apartment.getUser());
        List<Image> images = apartment.getImages();
        images.forEach((image)->{
            ImageResponse imageResponse = new ImageResponse(image.getFileName(),  image.getUrl(), image.getFileType(), image.getSize());
            listOfImageResponse.add(imageResponse);
        });
        apartmentDto.setImages(listOfImageResponse);
        apartmentDto.setApartmentType(apartment.getApartmentType());
        apartmentDto.setAddress(apartment.getAddress());
        apartmentDto.setBuildingType(apartment.getBuildingType());
        return apartmentDto;
    }

}
