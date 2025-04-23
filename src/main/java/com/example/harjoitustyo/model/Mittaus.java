package com.example.harjoitustyo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Mittaus {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tyyppi;
    private String arvo;
    private LocalDate pvm;

    @ManyToOne
    @JoinColumn(name = "henkilo_id")
    private Henkilo henkilo;
}
