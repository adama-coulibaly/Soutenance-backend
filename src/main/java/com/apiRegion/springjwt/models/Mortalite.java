package com.apiRegion.springjwt.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mortalite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idmortalite;
    private int nombredeces;
    private String causedeces;
    private LocalDate date;


    @ManyToOne
    private Production production;
}
