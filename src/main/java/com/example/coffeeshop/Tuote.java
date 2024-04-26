package com.example.coffeeshop;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.AbstractPersistable;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Tuote extends AbstractPersistable<Long> {

    private String nimi;
    private String kuvaus;
    private BigDecimal hinta;
    private String tuotekuva;
    @ManyToOne
    @JoinColumn(name = "osasto_id")
    private Osasto osasto;
}
