package com.example.coffeeshop;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ValmistajaRepository extends JpaRepository<Valmistaja, Long> {
    boolean existsByNimi(String nimi);
}
