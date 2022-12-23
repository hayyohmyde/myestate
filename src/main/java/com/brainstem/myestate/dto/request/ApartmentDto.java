package com.brainstem.myestate.dto.request;

import com.brainstem.myestate.model.Address;
import com.brainstem.myestate.model.User;
import com.brainstem.myestate.utils.ApartmentType;
import com.brainstem.myestate.utils.BuildingType;
import com.brainstem.myestate.utils.Status;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class ApartmentDto {
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
        private BigDecimal agencyFee;
        private BigDecimal commision;
        private BigDecimal otherFee;
        private String info;
        private MultipartFile[] files;
        private ApartmentType apartmentType;
        private Address address;
        private BuildingType buildingType;
}
