package com.apiRegion.springjwt.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationSender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idnotif;
    private LocalDate datedenvoi;
    private String titrenotification;
    private String messagenotification;

    @ManyToOne
    private User user;

    public NotificationSender(LocalDate now, String titreformation, String s) {
        this.datedenvoi = now;
        this.titrenotification = titreformation;
        this.messagenotification = s;
    }
}
