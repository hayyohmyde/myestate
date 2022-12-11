package com.brainstem.myestate.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "wallet")
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wallet_seq")
    @SequenceGenerator(name = "wallet_seq")
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "wallet_address", unique = true)
    private UUID walletAddress = UUID.randomUUID();
    private BigDecimal amount = BigDecimal.valueOf(0);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Wallet wallet = (Wallet) o;
        return id != null && Objects.equals(id, wallet.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}