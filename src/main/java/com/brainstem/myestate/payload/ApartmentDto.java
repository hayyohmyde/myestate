package com.brainstem.myestate.payload;

import com.brainstem.myestate.model.Address;
import com.brainstem.myestate.model.Image;
import com.brainstem.myestate.model.User;
import com.brainstem.myestate.utils.ApartmentType;
import com.brainstem.myestate.utils.BuildingType;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;


@Setter
@Getter
public class ApartmentDto {
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
    private boolean isRent;
    private boolean isActive;
    private BigDecimal agencyFee;
    private BigDecimal commision;
    private BigDecimal otherFee;
    private String info;
    private User user;
    private List<?> images = null;
    private ApartmentType apartmentType;
    private Address address;
    private BuildingType buildingType;

    public void setId(Long id) {
        this.id = id;
    }

    public void setServiced(boolean serviced) {
        isServiced = serviced;
    }

    public void setRent(boolean rent) {
        isRent = rent;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
