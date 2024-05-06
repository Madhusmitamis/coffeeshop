package com.example.coffeeshop;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TuoteRepository extends JpaRepository<Tuote, Long> {
    boolean existsByNimi(String nimi);
}
