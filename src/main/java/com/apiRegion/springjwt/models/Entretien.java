package com.apiRegion.springjwt.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Entretien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long identretien;
    private String typeentretien;
    private String intervale;
    private LocalDate dateentretien;
    private LocalTime heuresentretien;


    @ManyToOne
    private Production production;


}
