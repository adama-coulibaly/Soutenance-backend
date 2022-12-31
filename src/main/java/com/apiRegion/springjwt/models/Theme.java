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
public class Theme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idtheme;
    private String titretheme;
    private Date dateposte;

    /*
    @OneToMany
    private List<Commentaire> commentaires = new ArrayList<>();
     */

    @ManyToOne
    private User user;
}
