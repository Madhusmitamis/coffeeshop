package com.example.coffeeshop;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TuoteRepository extends JpaRepository<Tuote, Long> {
        Tuote findByNimi(String nimi);

        @Query("SELECT t FROM Tuote t JOIN FETCH t.osasto o WHERE o.id = :osastoID OR o.osastoIdp = :osastoID")
        Page<Tuote> findProductsByOsastoID(@Param("osastoID") Long osastoID, Pageable pageable);

        @Query("SELECT t FROM Tuote t JOIN FETCH t.osasto o WHERE o.id IN :osastoIDs OR o.osastoIdp IN :osastoIDs")
        Page<Tuote> findProductsByOsastoIDIn(@Param("osastoIDs") List<Long> osastoIDs, Pageable pageable);
        // Page<Tuote> findByOsastoId(long osastoId, Pageable pageable);

        @Query("SELECT t FROM Tuote t WHERE LOWER(t.nimi)LIKE LOWER(CONCAT('%', :keyword, '%'))")
        Page<Tuote> searchTuotteetByNimi(@Param("keyword") String keyword, Pageable pageable);
}
