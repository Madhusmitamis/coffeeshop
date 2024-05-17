package com.example.coffeeshop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValmistajaService {
    @Autowired
    ValmistajaRepository valmistajaRepository;

    @Autowired

    public List<Valmistaja> findAllValmistajat() {
        return valmistajaRepository.findAll();
    }

    public Valmistaja findValmistajaById(Long id) {
        return valmistajaRepository.findById(id).orElse(null);
    }

    public boolean existsByNimi(String nimi) {
        return valmistajaRepository.existsByNimi(nimi);
    }

    public void createValmistaja(String nimi, String url) {
        Valmistaja valmistaja = new Valmistaja();
        valmistaja.setNimi(nimi);
        valmistaja.setUrl(url);
        valmistajaRepository.save(valmistaja);
    }

    public void updateValmistaja(Long id, String nimi, String url) {
        Valmistaja valmistaja = valmistajaRepository.findById(id).orElse(null);
        if (valmistaja != null) {
            valmistaja.setNimi(nimi);
            valmistaja.setUrl(url);
            valmistajaRepository.save(valmistaja);
        }
    }

    public void deleteValmistaja(Long id) {
        valmistajaRepository.deleteById(id);

    }

}
