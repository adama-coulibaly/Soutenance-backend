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
public class Panier {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long idpanier;
        private Integer nombreproduit;

        @OneToOne
        private User user;





}
