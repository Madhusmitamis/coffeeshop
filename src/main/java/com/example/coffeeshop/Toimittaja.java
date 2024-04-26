package com.example.coffeeshop;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.AbstractPersistable;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Toimittaja extends AbstractPersistable<Long> {
    private String nimi;
    private String yhteyshenkilo;
    private String yhteyshenkilonEmail;
    @OneToMany(mappedBy = "toimittaja")
    private List<Tuote> tuotteet = new ArrayList<>();
}
