package com.apiRegion.springjwt.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategorieFormation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titreCategorie;
    private String photocategorie;
    private String description;
    private Long nbreTuto = Long.valueOf(0);
    private boolean etat = true;


}




