package com.example.harjoitustyo.model;

import jakarta.persistence.*;

@Entity
public class Profiili {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String kuvaus;

    @OneToOne
    @JoinColumn(name = "henkilo_id")
    private Henkilo henkilo;
}
