package com.brainstem.myestate.model;

import com.brainstem.myestate.utils.Gender;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "other_name")
    private String otherName;
//    private LocalDate dob;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(unique = true, nullable = false)
    private String email;
    private String username;
    private String password;
    @Column(unique = true)
    private String phoneNumber;

//    @Transient
//    private int age;

    @Column(name = "registered_date")
    private LocalDateTime registeredDate = LocalDateTime.now();

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

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles;

    @OneToOne( fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id")
    private Image profileImage;

    @OneToOne( fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Apartment> apartments = new HashSet<>();

    @JsonManagedReference
    @OneToMany( fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Building> buildings = new HashSet<>();

    @JsonManagedReference
    @OneToMany( fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Land> lands = new HashSet<>();

//    public int getAge() {
//        return Period.between(this.dob, LocalDate.now()).getYears();
//    }

    public Gender gender(Gender gender) {
        return this.gender;
    }

    public void setGender(Gender gender){ this.gender = gender; }

    public String getFullName(){
        return this.firstName + " " + this.lastName;
    }

    public void addLand(Land land){
        this.lands.add(land);

    }
    public void remove(Land land){
        this.lands.remove(land);
    }

    public void addApartment(Apartment apartment){
        this.apartments.add(apartment);
    }

    public void addBuilding(Building building){
        this.buildings.add(building);
    }

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
