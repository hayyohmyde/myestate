package com.brainstem.myestate.service;

import com.brainstem.myestate.dto.request.ApartmentDto;
import com.brainstem.myestate.dto.response.ApartmentDtoResponse;

import java.util.List;


public interface ApartmentService {

    ApartmentDtoResponse createApartment(ApartmentDto apartmentInputObject) throws Exception;

    List<ApartmentDtoResponse> getAllApartments(int pageNumber, int pageSize);

    ApartmentDtoResponse getApartmentById(long id);

    ApartmentDtoResponse updateApartment(long id, ApartmentDto apartmentInputObject);

    void deleteApartment(long id);

    List<ApartmentDtoResponse> searchApartments(String query);

}
