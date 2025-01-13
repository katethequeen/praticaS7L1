package com.example.praticaS7L1.controller;

import com.example.praticaS7L1.entities.Stato;
import com.example.praticaS7L1.entities.Viaggio;
import com.example.praticaS7L1.services.ViaggioSrv;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/viaggi")
public class ViaggioController {
    @Autowired
    private ViaggioSrv viaggioSrv;

    @GetMapping
    public ResponseEntity<List<Viaggio>> trovaTuttiViaggi() {
        List<Viaggio> viaggi = viaggioSrv.trovaTuttiViaggi();
        return ResponseEntity.ok(viaggi);
    }

    @GetMapping("/{id}")
    public ResponseEntity <Viaggio> trovaViaggioPerId(@PathVariable Long id) {
        Viaggio viaggio = viaggioSrv.getViaggioById(id);
        return ResponseEntity.ok(viaggio);
    }

    @PostMapping
    public ResponseEntity<Viaggio> creaViaggio(@Valid @RequestBody Viaggio viaggio) {
        Viaggio nuovoViaggio = viaggioSrv.creaViaggio(viaggio);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuovoViaggio);
    }


    @PutMapping("/{id}/stato")
    public ResponseEntity<Viaggio> aggiornaStatoViaggio(@PathVariable Long id, @RequestBody Stato nuovoStato) {
        Viaggio viaggioAggiornato = viaggioSrv.updateStato(id, nuovoStato);
        return ResponseEntity.ok(viaggioAggiornato);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminaViaggio(@PathVariable Long id) {
        viaggioSrv.eliminaViaggio(id);
        return ResponseEntity.noContent().build();
    }
}
