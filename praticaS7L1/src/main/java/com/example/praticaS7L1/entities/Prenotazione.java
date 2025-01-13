package com.example.praticaS7L1.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "prenotazioni")
public class Prenotazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "viaggio_id")
    private Viaggio viaggio;

    @Column(name = "data_prenotazione")
    private LocalDate dataPrenotazione;

    @NotBlank(message = "La nota è obbligatoria")
    private String nota;

    @ManyToOne
    @JoinColumn(name = "dipendente_id")
    private Dipendente dipendente;
}
