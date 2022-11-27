package com.brainstem.myestate.payload;

import com.brainstem.myestate.model.Address;
import com.brainstem.myestate.model.User;
import com.brainstem.myestate.utils.ApartmentType;
import com.brainstem.myestate.utils.BuildingType;
import lombok.Data;

import java.math.BigDecimal;


@Data
public class ApartmentDto {
    private Long id;
    private String owner;
    private String noOfRooms;
    private String noOfSittingRooms;
    private String noOfToilets;
    private String noOfKitchens;
    private String noOfWardropes;
    private String noOfParkingSpace;
    private boolean estateResidence;
    private String hoursOfElectricity;
    private String hoursOfWaterSupply;
    private boolean serviced;
    private BigDecimal amount;
    private boolean forRent;
    private BigDecimal agencyFee;
    private BigDecimal commision;
    private BigDecimal otherFee;
    private String info;
    private User user;
    private String frontImage;
    private String sittingRoomImage;
    private String bedroom1Image;
    private String bedroom2Image;
    private String kitchenImage;
    private String toiletImage;
    private String backImage;
    private ApartmentType apartmentType;
    private Address address;
    private BuildingType buildingType;

    public boolean getForRent() {
        return this.forRent;
    }
}
