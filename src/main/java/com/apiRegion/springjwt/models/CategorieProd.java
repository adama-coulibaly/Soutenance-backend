package com.apiRegion.springjwt.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategorieProd {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idcategorie;
    private String nomcategories;
}
