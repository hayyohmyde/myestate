package com.brainstem.myestate.service;

import com.brainstem.myestate.payload.LandDto;
import java.util.List;

public interface LandService {
    LandDto createLand(LandDto landDto);
    List<LandDto> getAllLands(int pageNumber, int pageSize);
    LandDto getLandById(long id);
    LandDto updateLand(long id, LandDto landDto);
    void deleteLand(long id);
}