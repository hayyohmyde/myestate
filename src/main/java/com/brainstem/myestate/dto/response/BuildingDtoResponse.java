package com.brainstem.myestate.dto.response;

import com.brainstem.myestate.dto.request.LandDto;
import com.brainstem.myestate.model.Address;
import com.brainstem.myestate.model.User;
import com.brainstem.myestate.utils.*;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class BuildingDtoResponse {
    private Long id;
    private BuildingType buildingType;
    private Address address;
    private String owner;
    private String noOfApartments;
    private ApartmentType apartmentType;
    private Enum<Status> status;
    private BigDecimal amount;
    private BigDecimal agencyFee;
    private BigDecimal commision;
    private BigDecimal otherFee;
    private Enum<Document> document;
    private Enum<LandMeasurement> landMeasurement;
    private String info;
    private List<ImageResponse> images;
}