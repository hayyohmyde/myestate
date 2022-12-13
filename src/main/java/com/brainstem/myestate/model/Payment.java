package com.brainstem.myestate.model;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@ApiModel("Payment details model")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cardType;
    private String cardName;
    private String cardNumber;
    private int expiryYear;
    private int expiryMonth;
    private int cvv;
    private Long orderId;

}
