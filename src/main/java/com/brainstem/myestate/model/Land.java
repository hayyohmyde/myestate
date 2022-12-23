package com.brainstem.myestate.model;

import com.brainstem.myestate.utils.Document;
import com.brainstem.myestate.utils.LandMeasurement;
import com.brainstem.myestate.utils.Status;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
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

    @Column(name = "no_of_pieces")
    private String noOfPieces;

    @Column(name = "document")
    private Document document;

    private LandMeasurement measurement;

    @Column(name = "is_estate_land")
    private Boolean isEstateLand;

    private Status status;


    @Column(name = "agency_fee")
    private BigDecimal agencyFee;

    private BigDecimal commision;

    @Column(name = "other_fee")
    private BigDecimal otherFee;

    private String info;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "plotNo", column = @Column(name = "plot_no")),
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

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Image> landImages;

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
