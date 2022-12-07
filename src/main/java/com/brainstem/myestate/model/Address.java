package com.brainstem.myestate.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@AllArgsConstructor
@NoArgsConstructor
//@Builder //makes nullable == true for the fields
@Data
@Embeddable
public class Address {
    private String flatNo;
    private String street;
    private String nearestJunction;
    private boolean estate;
    private String city;
    private String lga;
    private String state;
    private String country;
    private String location;

}