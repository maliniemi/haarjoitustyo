package com.example.harjoitustyo.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Kayttaja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String kayttajatunnus;
    private String salasana;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roolit;
}
