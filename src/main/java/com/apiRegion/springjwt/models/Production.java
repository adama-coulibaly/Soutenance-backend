package com.apiRegion.springjwt.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
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
    private LocalDate dateentrer;
    private LocalDate datesortie;
    private int quantite;
    private boolean etat = true;


    @ManyToMany
    private List<Ferme> ferme = new ArrayList<>();

    @ManyToOne
    private Typeproduction typeproduction;



}
