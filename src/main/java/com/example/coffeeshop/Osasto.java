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
public class Osasto extends AbstractPersistable<Long> {

    private String nimi;
    private Long osastoId;
    @OneToMany(mappedBy = "osasto")
    private List<Tuote> tuotteet = new ArrayList<>();

}
