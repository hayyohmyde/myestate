package com.brainstem.myestate.dto.response;

import com.brainstem.myestate.model.Address;
import com.brainstem.myestate.model.Image;
import com.brainstem.myestate.utils.Document;
import com.brainstem.myestate.utils.LandMeasurement;
import com.brainstem.myestate.utils.Status;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class LandDtoResponse {
    private Long id;
    private String owner;
    private BigDecimal amount;
    private String noOfPieces;
    private Document document;
    private LandMeasurement measurement;
    private Boolean isEstateLand;
    private Status status;
    private BigDecimal agencyFee;
    private BigDecimal commision;
    private BigDecimal otherFee;
    private String info;
    private Address address;
    private List<ImageResponse> landImages;

}
