package com.example.praticaS7L1.controller;

import com.example.praticaS7L1.entities.Prenotazione;
import com.example.praticaS7L1.services.PrenotazioneSrv;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {
    @Autowired
    private PrenotazioneSrv prenotazioneSrv;

    @GetMapping
    public ResponseEntity<List<Prenotazione>> trovaTuttePrenotazioni() {
        List<Prenotazione> prenotazioni = prenotazioneSrv.trovaTuttePrenotazioni();
        return ResponseEntity.ok(prenotazioni);
    }

    @GetMapping("/{id}")
    public ResponseEntity <Prenotazione> trovaPrenotazionePerId(@PathVariable Long id) {
        Prenotazione prenotazione = prenotazioneSrv.trovaPrenotazionePerId(id);
        return ResponseEntity.ok(prenotazione);
    }

    @PostMapping
    public ResponseEntity<Prenotazione> creaPrenotazione(@Valid @RequestBody Prenotazione prenotazione) {
        Prenotazione nuovaPrenotazione = prenotazioneSrv.creaPrenotazione(prenotazione);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuovaPrenotazione);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prenotazione> aggiornaPrenotazione(@PathVariable Long id, @Valid @RequestBody Prenotazione prenotazione) {
        Prenotazione prenotazioneAggiornata = prenotazioneSrv.aggiornaPrenotazione(id, prenotazione);
        return ResponseEntity.ok(prenotazioneAggiornata);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminaPrenotazione(@PathVariable Long id) {
        prenotazioneSrv.eliminaPrenotazione(id);
        return ResponseEntity.noContent().build();
    }
}
