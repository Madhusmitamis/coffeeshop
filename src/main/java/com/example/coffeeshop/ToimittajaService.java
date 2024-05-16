package com.example.coffeeshop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToimittajaService {
    @Autowired
    private ToimittajaRepository toimittajaRepository;

    public List<Toimittaja> findAllToimittajat() {
        return toimittajaRepository.findAll();
    }

    public Toimittaja findToimittajaById(Long id) {
        return toimittajaRepository.findById(id).orElse(null);
    }

    public Toimittaja addToimittaja(String nimi, String yhteyshenkilo, String yheteyshenkilonEmail) {
        Toimittaja toimittaja = new Toimittaja();
        toimittaja.setNimi(nimi);
        toimittaja.setYhteyshenkilo(yhteyshenkilo);
        toimittaja.setYhteyshenkilonEmail(yheteyshenkilonEmail);
        return toimittajaRepository.save(toimittaja);
    }

    public Toimittaja updateToimittaja(Long id, String nimi, String yhteyshenkilo, String yhteyshenkilonEmail) {
        Toimittaja toimittaja = toimittajaRepository.findById(id).orElse(null);
        if (toimittaja == null) {
            return null;
        }
        toimittaja.setNimi(nimi);
        toimittaja.setYhteyshenkilo(yhteyshenkilo);
        toimittaja.setYhteyshenkilonEmail(yhteyshenkilonEmail);
        return toimittajaRepository.save(toimittaja);
    }

    public void deleteToimittaja(Long id) {
        toimittajaRepository.deleteById(id);
    }

}
