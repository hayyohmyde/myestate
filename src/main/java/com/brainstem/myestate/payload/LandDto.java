package com.brainstem.myestate.payload;

import com.brainstem.myestate.model.Address;
import com.brainstem.myestate.model.User;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class LandDto {
    private Long id;
    private String owner;
    private BigDecimal amount;
    private String noOfPlots;
    private Boolean cOfO;
    private String measure;
    private Boolean receipt;
    private Boolean survey;
    private Boolean estateLand;
    private boolean forRent;
    private BigDecimal agencyFee;
    private BigDecimal commision;
    private BigDecimal otherFee;
    private String info;
    private User user;
    private Address address;
    private String frontImage;
    private String sittingRoomImage;
    private String bedroom1Image;
    private String bedroom2Image;
    private String kitchenImage;
    private String toiletImage;
    private String backImage;

}
