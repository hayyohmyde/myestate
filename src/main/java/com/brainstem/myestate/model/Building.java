package com.brainstem.myestate.model;

import com.brainstem.myestate.utils.*;
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
@Builder
@Entity
@Table(name = "buildings")
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Enumerated(EnumType.STRING)
    private BuildingType buildingType;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "buildingNo", column = @Column(name = "building_no")),
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

    private String owner;

    @Column(name = "no_of_apartments")
    private String noOfApartments;

    @Enumerated(EnumType.STRING)
    private ApartmentType apartmentType;

    private Status status;

    private BigDecimal amount;

    @Column(name = "agency_fee")
    private BigDecimal agencyFee;

    private BigDecimal commision;

    @Column(name = "other_fee")
    private BigDecimal otherFee;

    private Document document;

    @Column(name = "land_measure")
    private LandMeasurement landMeasurement;

    private String info;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Image> images;

//    public LandMeasurement getLandMeasurement() { return landMeasurement; }
//
//    public void setLandMeasurement(LandMeasurement landMeasurement) {
//        this.landMeasurement = landMeasurement;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Building building = (Building) o;
        return id != null && Objects.equals(id, building.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
