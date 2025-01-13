package com.example.praticaS7L1.services;

import com.example.praticaS7L1.entities.Dipendente;
import com.example.praticaS7L1.entities.Prenotazione;
import com.example.praticaS7L1.entities.Stato;
import com.example.praticaS7L1.entities.Viaggio;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.concurrent.TimeUnit;

@Service
public class FakerDataSrv {
    private final Faker faker = new Faker();

    @Autowired
    private DipendenteSrv dipendenteSrv;
    @Autowired
    private ViaggioSrv viaggioSrv;
    @Autowired
    private PrenotazioneSrv prenotazioneSrv;

    public Dipendente generaDipendente() {
        Dipendente dipendente = new Dipendente();
        dipendente.setUsername(faker.name().username());
        dipendente.setNome(faker.name().firstName());
        dipendente.setCognome(faker.name().lastName());
        dipendente.setEmail(faker.internet().emailAddress());
        return dipendenteSrv.creaDipendente(dipendente);
    }

    public Viaggio generaViaggio() {
        Viaggio viaggio = new Viaggio();
        viaggio.setDestinazione(faker.country().capital());
        viaggio.setData(faker.date().future(365, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        viaggio.setStato(Stato.IN_PROGRAMMA);
        return viaggioSrv.creaViaggio(viaggio);
    }

    public Prenotazione generaPrenotazione(Dipendente dipendente, Viaggio viaggio) {
        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setDipendente(dipendente);
        prenotazione.setViaggio(viaggio);
        prenotazione.setNota(faker.lorem().sentence());
        prenotazione.setDataPrenotazione(LocalDate.now());
        return prenotazioneSrv.creaPrenotazione(prenotazione);
    }
}
