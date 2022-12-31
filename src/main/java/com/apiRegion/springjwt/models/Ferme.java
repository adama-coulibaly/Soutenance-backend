package com.apiRegion.springjwt.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ferme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idferme;

    private String nomferme;
    private  String activiteferme;
    private String adresseferme;
    private String imageferme;
    private boolean etat;


    @ManyToOne
    private User user;

    @ManyToMany
    private List<Produit> produits;


}
