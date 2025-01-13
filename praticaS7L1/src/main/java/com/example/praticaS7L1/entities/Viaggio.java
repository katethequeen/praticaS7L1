package com.example.praticaS7L1.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "viaggi")
public class Viaggio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "La destinazione è obbligatoria")
    private String destinazione;

    @Future(message = "La data deve essere futura")
    private LocalDate data;

    @Enumerated(EnumType.STRING)
    private Stato stato;
}
