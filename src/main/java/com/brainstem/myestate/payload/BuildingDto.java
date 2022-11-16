package com.brainstem.myestate.payload;

import com.brainstem.myestate.model.Address;
import com.brainstem.myestate.model.User;
import com.brainstem.myestate.utils.ApartmentType;
import com.brainstem.myestate.utils.BuildingType;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

@Builder
@Data
public class BuildingDto {
    private Long id;
    private BuildingType buildingType;
    private Address address;
    private String owner;
    private String noOfApartments;
    private ApartmentType apartmentType;
    private Boolean forRent;
    private BigDecimal amount;
    private BigDecimal agencyFee;
    private BigDecimal commision;
    private BigDecimal otherFee;
    private boolean cOfO;
    private boolean surveyDocument;
    private boolean designPlan;
    private  boolean receipt;
    private boolean otherRelevantDocument;
    private boolean landMeasure;
    private String info;
    private User user;
}
