package com.example.praticaS7L1;

import com.example.praticaS7L1.entities.Dipendente;
import com.example.praticaS7L1.entities.Prenotazione;
import com.example.praticaS7L1.entities.Stato;
import com.example.praticaS7L1.entities.Viaggio;
import com.example.praticaS7L1.services.DipendenteSrv;
import com.example.praticaS7L1.services.FakerDataSrv;
import com.example.praticaS7L1.services.PrenotazioneSrv;
import com.example.praticaS7L1.services.ViaggioSrv;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Scanner;

@Component
public class MenuRunner implements CommandLineRunner {
    private final DipendenteSrv dipendenteSrv;
    private final ViaggioSrv viaggioSrv;
    private final PrenotazioneSrv prenotazioneSrv;
    private final FakerDataSrv fakerDataSrv;

    public MenuRunner(DipendenteSrv dipendenteSrv, ViaggioSrv viaggioSrv, PrenotazioneSrv prenotazioneSrv, FakerDataSrv fakerDataSrv) {
        this.dipendenteSrv = dipendenteSrv;
        this.viaggioSrv = viaggioSrv;
        this.prenotazioneSrv = prenotazioneSrv;
        this.fakerDataSrv = fakerDataSrv;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Aggiungi Dipendente");
            System.out.println("2. Aggiungi Viaggio");
            System.out.println("3. Aggiungi Prenotazione");
            System.out.println("4. Genera Dati Fittizi");
            System.out.println("5. Esci");
            System.out.print("Seleziona un'opzione: ");
            int scelta = scanner.nextInt();
            scanner.nextLine();
            switch (scelta) {
                case 1:
                    Dipendente dipendente = new Dipendente();
                    System.out.print("Username: ");
                    dipendente.setUsername(scanner.nextLine());
                    System.out.print("Nome: ");
                    dipendente.setNome(scanner.nextLine());
                    System.out.print("Cognome: ");
                    dipendente.setCognome(scanner.nextLine());
                    System.out.print("Email: ");
                    dipendente.setEmail(scanner.nextLine());
                    dipendenteSrv.creaDipendente(dipendente);
                    System.out.println("Dipendente aggiunto!");
                    break;
                case 2:
                    Viaggio viaggio = new Viaggio();
                    System.out.print("Destinazione: ");
                    viaggio.setDestinazione(scanner.nextLine());
                    System.out.print("Data (YYYY-MM-DD): ");
                    viaggio.setData(LocalDate.parse(scanner.nextLine()));
                    viaggio.setStato(Stato.IN_PROGRAMMA);
                    viaggioSrv.creaViaggio(viaggio);
                    System.out.println("Viaggio aggiunto!");
                    break;
                case 3:
                    System.out.print("Id Dipendente: ");
                    Long id = scanner.nextLong();
                    System.out.print("ID Viaggio: ");
                    Long viaggioId = scanner.nextLong();
                    scanner.nextLine();
                    Viaggio viaggioPrenotato = viaggioSrv.getViaggioById(viaggioId);
                    if (prenotazioneSrv.isDipendenteDisponibile(id, viaggioPrenotato.getData())) {
                        Prenotazione prenotazione = new Prenotazione();
                        prenotazione.setDipendente(dipendenteSrv.getDipendente(id));
                        prenotazione.setViaggio(viaggioPrenotato);
                        System.out.print("Nota: ");
                        prenotazione.setNota(scanner.nextLine());
                        prenotazione.setDataPrenotazione(LocalDate.now());
                        prenotazioneSrv.creaPrenotazione(prenotazione);
                        System.out.println("Prenotazione aggiunta!");
                    } else {
                        System.out.println("Il dipendente è già prenotato per un altro viaggio in questa data.");
                    }
                    break;
                case 4:
                    Dipendente dipFittizio = fakerDataSrv.generaDipendente();
                    Viaggio viaggioFittizio = fakerDataSrv.generaViaggio();
                    Prenotazione prenotazioneFittizia = fakerDataSrv.generaPrenotazione(dipFittizio, viaggioFittizio);
                    System.out.println("Generato dipendente fittizio: " + dipFittizio);
                    System.out.println("Generato viaggio fittizio: " + viaggioFittizio);
                    System.out.println("Generata prenotazione fittizia: " + prenotazioneFittizia);
                    break;
                case 5:
                    System.out.println("Uscita...");
                    scanner.close();
                    System.exit(0);
                    break;
            }
        }
    }
}
