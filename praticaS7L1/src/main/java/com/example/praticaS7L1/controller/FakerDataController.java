package com.example.praticaS7L1.controller;

import com.example.praticaS7L1.entities.Dipendente;
import com.example.praticaS7L1.entities.Prenotazione;
import com.example.praticaS7L1.entities.Viaggio;
import com.example.praticaS7L1.services.FakerDataSrv;
import com.example.praticaS7L1.services.PrenotazioneSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fakerdata")
public class FakerDataController {
    @Autowired
    private FakerDataSrv fakerDataSrv;

    @Autowired
    private PrenotazioneSrv prenotazioneSrv;

    @GetMapping("/dipendente")
    public Dipendente generaDipendente() {
        return fakerDataSrv.generaDipendente();
    }
    @GetMapping("/viaggio")
    public Viaggio generaViaggio() {
        return fakerDataSrv.generaViaggio();
    }
    @GetMapping("/prenotazione")
    public Prenotazione generaPrenotazione() {
        Dipendente dipendente = fakerDataSrv.generaDipendente();
        Viaggio viaggio = fakerDataSrv.generaViaggio();
        return fakerDataSrv.generaPrenotazione(dipendente, viaggio);
    }
}
