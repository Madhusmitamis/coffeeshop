package com.example.coffeeshop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OsastoService {
    @Autowired
    private OsastoRepository osastoRepository;

    public List<Osasto> findAllOsastot() {
        return osastoRepository.findAll();
    }

    public Osasto findOsastoById(Long id) {
        return osastoRepository.findById(id).orElse(null);
    }

    public void createOsasto(String nimi, Long osastoIdp) {
        if (osastoRepository.existsByNimi(nimi)) {
            throw new IllegalArgumentException("Department with name " + nimi + " already exists.");
        }
        Osasto osasto = new Osasto();
        osasto.setNimi(nimi);
        osasto.setOsastoIdp(osastoIdp);
        osastoRepository.save(osasto);
    }

    public void updateOsasto(Long id, String nimi, Long osastoIdp) {
        Osasto osasto = osastoRepository.findById(id).orElse(null);
        if (osasto == null) {
            throw new IllegalArgumentException("Department with ID " + id + " not found.");
        }
        osasto.setNimi(nimi);
        osasto.setOsastoIdp(osastoIdp);
        osastoRepository.save(osasto);
    }

    public void deleteOsasto(Long id) {
        osastoRepository.deleteById(id);
        // You might want to handle related entities before deletion
    }
}
