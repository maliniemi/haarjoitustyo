package com.example.harjoitustyo.repository;

import com.example.harjoitustyo.model.Mittaus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MittausRepository extends JpaRepository<Mittaus, Long> {
}
