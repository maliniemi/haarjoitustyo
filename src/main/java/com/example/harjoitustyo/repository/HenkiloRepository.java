package com.example.harjoitustyo.repository;

import com.example.harjoitustyo.model.Henkilo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HenkiloRepository extends JpaRepository<Henkilo, Long> {
    // Yksinkertainen suodatus yhdelle sarakkeelle
    List<Henkilo> findByNimiContainingIgnoreCase(String nimi);

    // Monisarakkeinen suodatus: nimi + osoite
    List<Henkilo> findByNimiContainingIgnoreCaseAndOsoiteContainingIgnoreCase(String nimi, String osoite);

}
