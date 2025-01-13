package com.example.praticaS7L1.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "dipendenti")
public class Dipendente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Username è obbligatorio")
    private String username;
    @NotBlank(message = "Nome è obbligatorio")
    private String nome;
    @NotBlank(message = "Cognome è obbligatorio")
    private String cognome;
    @NotBlank(message = "Email deve essere valida")
    private String email;

    @Lob
    private String immagineProfilo;
}
