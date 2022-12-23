package com.brainstem.myestate.dto.response;

import com.brainstem.myestate.dto.request.ApartmentDto;
import com.brainstem.myestate.model.Address;
import com.brainstem.myestate.utils.ApartmentType;
import com.brainstem.myestate.utils.BuildingType;
import com.brainstem.myestate.utils.Status;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
public class ApartmentDtoResponse{
    private Long id;
    private String owner;
    private String noOfRooms;
    private String noOfSittingRooms;
    private String noOfToilets;
    private String noOfKitchens;
    private String noOfWardropes;
    private String noOfParkingSpace;
    private boolean isEstateResidence;
    private String hoursOfElectricity;
    private String hoursOfWaterSupply;
    private boolean isServiced;
    private BigDecimal amount;
    private Enum<Status> status;
    private boolean isActive = true;
    private BigDecimal agencyFee;
    private BigDecimal commision;
    private BigDecimal otherFee;
    private String info;
    private List<ImageResponse> images;
    private ApartmentType apartmentType;
    private Address address;
    private BuildingType buildingType;
}
