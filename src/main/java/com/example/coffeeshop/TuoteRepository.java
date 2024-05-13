package com.example.coffeeshop;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TuoteRepository extends JpaRepository<Tuote, Long> {
    Tuote existsByNimi(String nimi);

    @Query("SELECT t FROM Tuote t WHERE t.osasto.nimi = 'kahvilaitteet'")
    List<Tuote> findKahvilaitteet();
}
