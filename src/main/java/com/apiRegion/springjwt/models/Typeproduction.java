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
public class Typeproduction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idtype;
    private String nomtype;

/*
    @OneToMany
    private List<Production> productions = new ArrayList<>();

 */

}
