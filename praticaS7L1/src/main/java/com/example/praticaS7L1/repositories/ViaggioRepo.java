package com.example.praticaS7L1.repositories;

import com.example.praticaS7L1.entities.Viaggio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViaggioRepo extends JpaRepository<Viaggio, Long> {
}
