package com.apiRegion.springjwt.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatusUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idstatus;
    private String nomstatus;
}
