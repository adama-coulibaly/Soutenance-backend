package com.apiRegion.springjwt.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Production {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idproduction;
    private Date dateentrer;
    private Date datesortie;
    private Number quantite;
    private boolean etat;


    @ManyToMany
    private List<Ferme> ferme;

    @ManyToOne
    private Typeproduction typeproduction;

/*
    @OneToMany
    private List<Entretien> entretiens = new ArrayList<>();

    @OneToMany
    private List<Mortalite> mortalites = new ArrayList<>();
    */
}
