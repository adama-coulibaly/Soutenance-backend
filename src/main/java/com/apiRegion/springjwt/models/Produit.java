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
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idproduit;
    private String nomproduit;
    private String reference;
    private String descriptionproduit;
    private String phtoproduit;
    private boolean etat = true;


    @ManyToMany
    private List<Ferme> fermes = new ArrayList<>();

    /*@ManyToOne()
    private Panier panier;

     */

}
