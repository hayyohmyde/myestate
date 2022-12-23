package com.brainstem.myestate.model;

import com.brainstem.myestate.utils.Gender;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "user_gen")
    @SequenceGenerator(name = "user_gen", sequenceName = "user_seq")
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Size(min = 3, message = "First name must be more than 3 characters")
    @Column(name = "first_name", nullable = true)
    private String firstName;

    @Size(min = 3, message = "Last name must be more than 3 characters")
    @Column(name = "last_name", nullable = true)
    private String lastName;

    @Column(name = "other_name")
    private String otherName;

    private LocalDate dob;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Email(message = "Email must be a valid one")
    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String username;

    @Size(min = 8,
            message = "Password should contain atleast 8 alphanumeric, one special character and a number")
    private String password;

    @NumberFormat(pattern = "^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]\\d{3}[\\s.-]?\\d{4}$")
    @Column(unique = true, nullable = true)
    private String phoneNumber;

    @Transient
    private int age;

    @CreationTimestamp
    @Column(name = "registered_date")
    private LocalDateTime registeredDate;

    @UpdateTimestamp
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

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

    private boolean isActive = true;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles;

    @OneToOne( fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private Image profileImage;

    @OneToOne( fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "wallet_id", referencedColumnName = "id")
    private Wallet wallet;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<Apartment> apartments = new HashSet<>();

    @JsonManagedReference
    @OneToMany( fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<Building> buildings = new HashSet<>();

    @JsonManagedReference
    @OneToMany( fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<Land> lands = new HashSet<>();

    public int getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public Gender getGender(Gender gender) { return this.gender; }
    public void setGender(Gender gender){ this.gender = gender; }
    public String getFullName(){ return this.firstName + " " + this.lastName;}
    public void addLand(Land land){ this.lands.add(land); }
    public void remove(Land land){ this.lands.remove(land);}
    public void addApartment(Apartment apartment){ this.apartments.add(apartment);}
    public void addBuilding(Building building){ this.buildings.add(building);}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
