package com.brainstem.myestate.payload;

import com.brainstem.myestate.model.Address;
import com.brainstem.myestate.model.Image;
import com.brainstem.myestate.model.User;
import com.brainstem.myestate.utils.ApartmentType;
import com.brainstem.myestate.utils.BuildingType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Getter
@Setter
public class ApartmentInputObject {
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
        private BigDecimal agencyFee;
        private BigDecimal commision;
        private BigDecimal otherFee;
        private String info;
        private User user;
        private MultipartFile[] files = null;
        private ApartmentType apartmentType;
        private Address address;
        private BuildingType buildingType;

}
