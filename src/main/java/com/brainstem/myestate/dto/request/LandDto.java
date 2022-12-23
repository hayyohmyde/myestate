package com.brainstem.myestate.dto.request;

import com.brainstem.myestate.model.Address;
import com.brainstem.myestate.model.User;
import com.brainstem.myestate.utils.Document;
import com.brainstem.myestate.utils.LandMeasurement;
import com.brainstem.myestate.utils.Status;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Data
@Builder
public class LandDto {
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
    private MultipartFile[] files;

}
