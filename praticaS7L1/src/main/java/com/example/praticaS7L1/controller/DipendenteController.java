package com.example.praticaS7L1.controller;

import com.example.praticaS7L1.entities.Dipendente;
import com.example.praticaS7L1.services.DipendenteSrv;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/dipendenti")
public class DipendenteController {
    @Autowired
    private DipendenteSrv dipendenteSrv;

    @GetMapping
    public ResponseEntity<List<Dipendente>> trovaTuttiDipendenti() {
        List<Dipendente> dipendenti = dipendenteSrv.trovaTuttiDipendenti();
        return ResponseEntity.ok(dipendenti);
    }

    @GetMapping("/{id}")
    public ResponseEntity <Dipendente> trovaDipendentePerId(@PathVariable Long id) {
        Dipendente dipendente = dipendenteSrv.getDipendente(id);
        return ResponseEntity.ok(dipendente);
    }

    @PostMapping
    public ResponseEntity<Dipendente> creaDipendente(@Valid @RequestBody Dipendente dipendente) {
        Dipendente nuovoDipendente = dipendenteSrv.creaDipendente(dipendente);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuovoDipendente);
    }

    @PutMapping("/{id}/immagineProfilo")
    public ResponseEntity<Dipendente> aggiornaImmagineProfilo(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws Exception {
        Dipendente dipendenteAggiornato = dipendenteSrv.aggiornaImmagineProfilo(id, file);
        return ResponseEntity.ok(dipendenteAggiornato);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminaDipendente(@PathVariable Long id) {
        dipendenteSrv.eliminaDipendente(id);
        return ResponseEntity.noContent().build();
    }
}
