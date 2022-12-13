package com.brainstem.myestate.service;

import com.brainstem.myestate.model.Apartment;
import com.brainstem.myestate.payload.ApartmentDto;
import com.brainstem.myestate.payload.ApartmentInputObject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface ApartmentService {

    ApartmentDto createApartment(ApartmentInputObject apartmentInputObject) throws Exception;

    List<ApartmentDto> getAllApartments(int pageNumber, int pageSize);

    ApartmentDto getApartmentById(long id);

    ApartmentDto updateApartment(long id, ApartmentInputObject apartmentInputObject);

    void deleteApartment(long id);

    List<ApartmentDto> searchApartments(String query);

}
