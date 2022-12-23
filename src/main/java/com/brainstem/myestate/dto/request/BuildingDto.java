package com.brainstem.myestate.dto.request;

import com.brainstem.myestate.model.Address;
import com.brainstem.myestate.model.User;
import com.brainstem.myestate.utils.*;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
public class BuildingDto {
    @NotNull(message = "BuildingType must not be empty")
    private BuildingType buildingType;

    @NotNull(message = "Address must not be empty")
    private Address address;

    @NotNull(message = "Owner must not be empty")
    private String owner;

    @NotNull(message = "NoOfApartments field must not be empty")
    private String noOfApartments;

    @NotNull(message = "ApartmentType must not be empty")
    private ApartmentType apartmentType;

    @NotNull(message = "Status must be either FOR SALE or FOR RENT")
    private Status status;

    @NotNull(message = "Amount must not be empty")
    private BigDecimal amount;

    private BigDecimal agencyFee;
    private BigDecimal commision;
    private BigDecimal otherFee;
    private LandMeasurement landMeasurement;

    @NotNull(message = "Document must not be empty")
    private Document document;

    @NotNull(message = "Info field must not be empty")
    private String info;

    @NotNull(message = "Images must not be empty")
    private MultipartFile[] files;
}
