package com.example.harjoitustyo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Henkilo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nimi;
    private String osoite;

    @OneToMany(mappedBy = "henkilo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Mittaus> mittaukset;

    @OneToOne(mappedBy = "henkilo", cascade = CascadeType.ALL)
    private Profiili profiili;
}
