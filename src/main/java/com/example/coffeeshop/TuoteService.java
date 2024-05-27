package com.example.coffeeshop;

import java.util.Arrays;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class TuoteService {
    @Autowired
    private TuoteRepository tuoteRepository;
    @Autowired
    private ToimittajaRepository toimittajaRepository;
    @Autowired
    private ValmistajaRepository valmistajaRepository;
    @Autowired
    private OsastoRepository osastoRepository;

    public List<Tuote> findAllProducts() {
        return tuoteRepository.findAll();
    }

    public Tuote findProductById(Long id) {
        return tuoteRepository.findById(id).orElse(null);
    }

    public List<Osasto> findAllDepartments() {
        return osastoRepository.findAll();
    }

    public Page<Tuote> getProductForKahvilaitteet(Pageable pageable) {
        return tuoteRepository.findProductsByOsastoID(1L, pageable);
    }

    // public List<Tuote> getProductForKulutustuotteet() {
    // List<Tuote> productsForOsastoId2 =
    // tuoteRepository.findProductsByOsastoID(2L);
    // List<Tuote> productsForOsastoId7 =
    // tuoteRepository.findProductsByOsastoID(7L);

    // // Combine the two lists
    // productsForOsastoId2.addAll(productsForOsastoId7);

    // return productsForOsastoId2;
    // }
    public Page<Tuote> getProductForKulutustuotteet(Pageable pageable) {
        return tuoteRepository.findProductsByOsastoIDIn(Arrays.asList(2L, 7L), pageable);
    }

    public List<Valmistaja> findAllValmistajat() {
        return valmistajaRepository.findAll();
    }

    public List<Toimittaja> findAllToimittajat() {
        return toimittajaRepository.findAll();
    }

    public Tuote addTuote(String nimi, String kuvaus, BigDecimal hinta,
            Long toimittajaid, Long valmistajaid,
            Long osastoid, byte[] bytes) {
        Valmistaja v = valmistajaRepository.findById(valmistajaid).orElse(null);
        Osasto o = osastoRepository.findById(osastoid).orElse(null);
        Toimittaja t = toimittajaRepository.findById(toimittajaid).orElse(null);
        Tuote tuote = tuoteRepository.findByNimi(nimi);
        if (tuote != null) {
            return tuote;

        }
        Tuote uusiTuote = new Tuote();
        uusiTuote.setNimi(nimi);
        uusiTuote.setKuvaus(kuvaus);
        uusiTuote.setHinta(hinta);
        uusiTuote.setKuva(bytes);

        uusiTuote.setValmistaja(v);
        uusiTuote.setOsasto(o);
        uusiTuote.setToimittaja(t);
        return tuoteRepository.save(uusiTuote);

    }

    public void updateTuote(Long id, String nimi, String kuvaus, BigDecimal hinta,
            Long toimittajaid, Long valmistajaid, Long osastoid) {
        Tuote tuote = tuoteRepository.findById(id).orElse(null);
        if (tuote == null) {
            throw new IllegalArgumentException("Product with ID " + id + " not found.");
        }
        Toimittaja toimittaja = toimittajaRepository.findById(toimittajaid).orElse(null);
        Valmistaja valmistaja = valmistajaRepository.findById(valmistajaid).orElse(null);
        tuote.setNimi(nimi);
        tuote.setKuvaus(kuvaus);
        tuote.setHinta(hinta);
        tuote.setToimittaja(toimittaja);
        tuote.setValmistaja(valmistaja);
        Osasto osasto = osastoRepository.findById(osastoid).orElse(null);
        tuote.setOsasto(osasto);

        tuoteRepository.save(tuote);
    }

    public void deleteTuote(Long id) {
        tuoteRepository.deleteById(id);
    }

}
