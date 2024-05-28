package com.example.coffeeshop;

import org.springframework.data.jpa.domain.AbstractPersistable;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class VipCustomer extends AbstractPersistable<Long> {
    private String etunimi;
    private String sukunimi;
    private String sahkopostiosoite;
}
