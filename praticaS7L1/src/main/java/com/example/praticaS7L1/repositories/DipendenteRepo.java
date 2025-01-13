package com.example.praticaS7L1.repositories;

import com.example.praticaS7L1.entities.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DipendenteRepo extends JpaRepository<Dipendente, Long> {
}
