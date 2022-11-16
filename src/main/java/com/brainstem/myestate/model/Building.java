package com.brainstem.myestate.model;

import com.brainstem.myestate.utils.ApartmentType;
import com.brainstem.myestate.utils.BuildingType;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
//@NoArgsConstructor
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

    private String owner;

    @Column(name = "no_of_apartments")
    private String noOfApartments;

    @Enumerated(EnumType.STRING)
    private ApartmentType apartmentType;

    @Column(name = "for_rent")
    private Boolean forRent;

    private BigDecimal amount;

    @Column(name = "agency_fee")
    private BigDecimal agencyFee;

    private BigDecimal commision;

    @Column(name = "other_fee")
    private BigDecimal otherFee;

    @Column(name = "c_of_o")
    private boolean cOfO;

    @Column(name = "survey_document")
    private boolean surveyDocument;

    @Column(name = "design_plan")
    private boolean designPlan;

    @Column(name = "receipt")
    private  boolean receipt;

    @Column(name = "other_relevant_document")
    private boolean otherRelevantDocument;

    @Column(name = "land_measure")
    private boolean landMeasure;

    private String info;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @ToString.Exclude
    private User user;

    public BuildingType getBuildingType() { return  this.buildingType; }

    public void setBuildingType(BuildingType buildingType){ this.buildingType = buildingType; }

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
