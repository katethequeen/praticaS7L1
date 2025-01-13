package com.example.praticaS7L1.repositories;

import com.example.praticaS7L1.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PrenotazioneRepo extends JpaRepository<Prenotazione, Long> {
    List<Prenotazione> findByDipendenteIdAndDataPrenotazione(Long dipendenteId, LocalDate dataPrenotazione);
}
