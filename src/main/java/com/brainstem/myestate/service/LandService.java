package com.brainstem.myestate.service;

import com.brainstem.myestate.dto.request.LandDto;
import com.brainstem.myestate.dto.response.LandDtoResponse;

import java.util.List;

public interface LandService {
    LandDtoResponse createLand(LandDto landDto) throws Exception;
    List<LandDtoResponse> getAllLands(int pageNumber, int pageSize);
    LandDtoResponse getLandById(long id);
    LandDtoResponse updateLand(long id, LandDto landDto) throws Exception;
    void deleteLand(long id);
}