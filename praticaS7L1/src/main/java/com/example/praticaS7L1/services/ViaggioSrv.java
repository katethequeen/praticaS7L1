package com.example.praticaS7L1.services;

import com.example.praticaS7L1.entities.Stato;
import com.example.praticaS7L1.entities.Viaggio;
import com.example.praticaS7L1.repositories.ViaggioRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViaggioSrv {
    @Autowired
    private ViaggioRepo viaggioRepo;

    public List<Viaggio> trovaTuttiViaggi() {
        return viaggioRepo.findAll();
    }


    public void eliminaViaggio(Long id) {
        viaggioRepo.deleteById(id);
    }

    public Viaggio creaViaggio(Viaggio viaggio) {
        return viaggioRepo.save(viaggio);
    }

    public Viaggio getViaggioById(Long id) {
        return viaggioRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Viaggio non trovato"));
    }

    public Viaggio updateStato(Long id, Stato stato) {
        Viaggio viaggio = viaggioRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Viaggio non trovato"));
        viaggio.setStato(stato);
        return viaggioRepo.save(viaggio);
    }
}
