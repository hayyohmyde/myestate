package com.brainstem.myestate.service;

import com.brainstem.myestate.payload.ApartmentDto;

import java.util.List;


public interface ApartmentService {
    ApartmentDto createApartment(
         ApartmentDto apartmentDto
    );

    List<ApartmentDto> getAllApartments(int pageNumber, int pageSize);

    ApartmentDto getApartmentById(long id);

    ApartmentDto updateApartment(long id, ApartmentDto apartmentDto);

    void deleteApartment(long id);

}
