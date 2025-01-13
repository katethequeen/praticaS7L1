package com.example.praticaS7L1.services;

import com.example.praticaS7L1.entities.Prenotazione;
import com.example.praticaS7L1.repositories.PrenotazioneRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PrenotazioneSrv {
    @Autowired
    private PrenotazioneRepo prenotazioneRepo;

    public boolean isDipendenteDisponibile(Long dipendenteId, LocalDate dataPrenotazione) {
        List<Prenotazione> prenotazioni = prenotazioneRepo.findByDipendenteIdAndDataPrenotazione(dipendenteId, dataPrenotazione);
        return prenotazioni.isEmpty();
    }

    public Prenotazione creaPrenotazione(Prenotazione prenotazione) {
        return prenotazioneRepo.save(prenotazione);
    }

    public Prenotazione aggiornaPrenotazione(Long id, Prenotazione prenotazione)  {
        Prenotazione prenotazioneEsistente = prenotazioneRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Prenotazione non trovata"));
        prenotazioneEsistente.setNota(prenotazione.getNota());
        prenotazioneEsistente.setDataPrenotazione(prenotazione.getDataPrenotazione());
        prenotazioneEsistente.setViaggio(prenotazione.getViaggio());
        prenotazioneEsistente.setDipendente(prenotazione.getDipendente());
        return prenotazioneRepo.save(prenotazioneEsistente);
    }

    public void eliminaPrenotazione(Long id) {
        prenotazioneRepo.deleteById(id);
    }

    public List<Prenotazione> trovaTuttePrenotazioni() {
        return prenotazioneRepo.findAll();
    }

    public Prenotazione trovaPrenotazionePerId(Long id) {
        return prenotazioneRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Prenotazione non trovata"));
    }
}
