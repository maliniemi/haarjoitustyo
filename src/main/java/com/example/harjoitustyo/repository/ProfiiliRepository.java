package com.example.harjoitustyo.repository;

import com.example.harjoitustyo.model.Profiili;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfiiliRepository extends JpaRepository<Profiili, Long> {

    @Query("SELECT p FROM Profiili p WHERE p.henkilo.nimi = :nimi")
    List<Profiili> findByHenkiloNimi(@Param("nimi") String nimi);
}
