package com.example.coffeeshop;

import java.io.IOException;
import java.math.BigDecimal;

import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
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
    @Lob
    private byte[] kuva;
    // private MultipartFile imageFile;

    @ManyToOne
    @JoinColumn(name = "osasto_id")
    private Osasto osasto;

    @ManyToOne
    @JoinColumn(name = "toimittaja_id")
    private Toimittaja toimittaja;

    @ManyToOne
    @JoinColumn(name = "valmistaja_id")
    private Valmistaja valmistaja;

    // public void setImage(byte[] imageBytes) {
    // this.kuva = imageBytes;
    // }

}
