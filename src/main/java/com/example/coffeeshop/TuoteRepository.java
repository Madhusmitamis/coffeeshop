package com.example.coffeeshop;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TuoteRepository extends JpaRepository<Tuote, Long> {
        Tuote findByNimi(String nimi);

        // List<Tuote> findByOsastoNimi(String osastoNimi);

        // @Query("SELECT t FROM Tuote t WHERE t.osasto.nimi = 'kahvilaitteet'")
        // List<Tuote> findKahvilaitteet();

        // List<Tuote> findByOsastoIdAndOsastoIdp(Long osastoId, Long osastoIdp);

        @Query("SELECT t FROM Tuote t JOIN FETCH t.osasto o WHERE o.id = :osastoID OR o.osastoIdp = :osastoID")
        List<Tuote> findProductsByOsastoID(@Param("osastoID") Long osastoID);

        // List<Tuote> findProductsByOsastoID(@Param("osastoID") Long osastoID
        // @Param("osastoIdp")Long osastoIdp);

}
