package com.brainstem.myestate.model;

import com.brainstem.myestate.payload.ImageResponse;
import com.brainstem.myestate.utils.ApartmentType;
import com.brainstem.myestate.utils.BuildingType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "apartments")
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    private String owner;

    @Column(name = "no_of_rooms")
    private String noOfRooms;

    @Column(name = "no_of_sitting_rooms")
    private String noOfSittingRooms;

    @Column(name = "no_of_toilets")
    private String noOfToilets;

    @Column(name = "no_of_kitchens")
    private String noOfKitchens;

    @Column(name = "no_of_wardropes")
    private String noOfWardropes;

    @Column(name = "no_of_parking_space")
    private String noOfParkingSpace;

    @Column(name = "is_estate_residence")
    private boolean isEstateResidence;

    @Column(name = "hours_of_electricity")
    private String hoursOfElectricity;

    @Column(name = "hours_of_water_supply")
    private String hoursOfWaterSupply;

    @Column(name = "is_serviced")
    private boolean isServiced;
    private BigDecimal amount;

    @Column(name="is_active")
    private boolean isActive = true;

    @Column(name = "is_rent")
    private boolean isRent;

    @Column(name = "agency_fee")
    private BigDecimal agencyFee;
    private BigDecimal commision;

    @Column(name = "other_fee")
    private BigDecimal otherFee;
    private String info;

//    @JsonBackReference
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "user_id" )
//    @ToString.Exclude
//    private User user;

    @Enumerated(EnumType.STRING)
    private ApartmentType apartmentType;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "flatNo", column = @Column(name = "flat_no")),
            @AttributeOverride(name = "street", column = @Column(name = "street")),
            @AttributeOverride(name = "nearestJunction", column = @Column(name = "nearest_junction")),
            @AttributeOverride(name = "estate", column = @Column(name = "estate")),
            @AttributeOverride(name = "city", column = @Column(name = "city")),
            @AttributeOverride(name = "lga", column = @Column(name = "lga")),
            @AttributeOverride(name = "state", column = @Column(name = "state")),
            @AttributeOverride(name = "country", column = @Column(name = "country")),
            @AttributeOverride(name = "location", column = @Column(name = "location"))
    })
    private Address address;

    @Enumerated(EnumType.STRING)
    private BuildingType buildingType;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Image> images;

    public ApartmentType getApartmentType(){ return this.apartmentType;}
    public void setApartmentType(ApartmentType apartmentType){ this.apartmentType = apartmentType; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Apartment apartment = (Apartment) o;
        return id != null && Objects.equals(id, apartment.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
    public boolean isRent() {
        return this.isRent;
    }
}
