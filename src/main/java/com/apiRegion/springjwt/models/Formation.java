package com.apiRegion.springjwt.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Formation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idformation;
    private LocalDate datedeposte;
    private String titreformation;
    private String photoformation;
    private String dureformation;
    private String urlformation;
    private String description;
    private boolean etat = true;

    @ManyToMany
    private List<User> users = new ArrayList<>();

    @ManyToOne()
    @JoinColumn(name = "id_categorie")
    private CategorieFormation categorieFormation;
}
