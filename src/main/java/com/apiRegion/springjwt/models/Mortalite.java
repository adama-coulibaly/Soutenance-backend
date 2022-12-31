package com.apiRegion.springjwt.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private Number nombredeces;
    private String causedeces;
    private Date  date;


    @ManyToOne
    private Production production;
}
