package com.brainstem.myestate.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "land")
public class Land {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    private String owner;
    private BigDecimal amount;

    @Column(name = "no_of_plots")
    private String noOfPlots;

    @Column(name = "c_of_o")
    private Boolean cOfO;

    private String measure;

    private Boolean receipt;

    private Boolean survey;

    @Column(name = "estate_land")
    private Boolean estateLand;

    @Column(name = "for_rent")
    private boolean forRent;

    @Column(name = "agency_fee")
    private BigDecimal agencyFee;

    private BigDecimal commision;

    @Column(name = "other_fee")
    private BigDecimal otherFee;

    private String info;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @ToString.Exclude
    private User user;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "flatNo", column = @Column(name = "plot_no")),
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

    @Column(name = "front_image")
    private String frontImage;

    @Column(name = "sitting_room_image")
    private String sittingRoomImage;

    @Column(name = "bedroom_image")
    private String bedroom1Image;

    @Column(name = "bedroom2_image")
    private String bedroom2Image;

    @Column(name = "kitchen_image")
    private String kitchenImage;

    @Column(name = "toilet_image")
    private String toiletImage;

    @Column(name = "back_image")
    private String backImage;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Land land = (Land) o;
        return id != null && Objects.equals(id, land.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
