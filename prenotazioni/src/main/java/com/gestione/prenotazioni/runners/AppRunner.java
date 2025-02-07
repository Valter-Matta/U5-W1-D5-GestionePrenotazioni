package com.gestione.prenotazioni.runners;


import com.gestione.prenotazioni.entity.Postazione;
import com.gestione.prenotazioni.entity.Prenotazione;
import com.gestione.prenotazioni.entity.Utente;
import com.gestione.prenotazioni.repo.PostazioneRepository;
import com.gestione.prenotazioni.repo.PrenotazioneRepository;
import com.gestione.prenotazioni.repo.UtenteRepository;
import com.gestione.prenotazioni.service.PrenotazioneService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Slf4j
@Component
@RequiredArgsConstructor
@Order (2)
public class AppRunner implements CommandLineRunner {
	private final PrenotazioneService prenotazioneService;
	private final UtenteRepository utenteRepository;
	private final PostazioneRepository postazioneRepository;


	@Override
	public void run (String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("Seleziona un azione:  ");
			System.out.println("1. Visualizza tutte le prenotazioni");
			System.out.println("2. Visualizza tutte le prenotazioni di un utente");
			System.out.println("3. Effettua una prenotazione");
			System.out.println("0. Per terminare");
			int scelta = scanner.nextInt();
			switch (scelta) {
				case 0:
					break;

				case 1:

					List<Prenotazione> prenotazioni = prenotazioneService.getAllPrenotazioni();
					prenotazioni.forEach(prenotazione -> System.out.println(
						"Prenotazione ID: " + prenotazione.getId() + " | dal giorno: " + prenotazione.getData() + " fino al: " + prenotazione.getDataScadenza() + "\n" +
							"Username: " + prenotazione.getUtente().getUsername() + "\n" +
							"Postazione descrizione: " + prenotazione.getPostazione().getDescrizione() +
							" | Tipo: " + prenotazione.getPostazione().getTipo() +
							"\nEdificio: " + prenotazione.getPostazione().getEdificio().getNome() + " | " + prenotazione.getPostazione().getEdificio().getCity() +
							"\n--------------------------------\n"
					));

					break;

				case 2:
					System.out.println("Digita l'ID dell'utente per vederne le prenotazioni ");
					Long id = scanner.nextLong();
					if (utenteRepository.findById(id).isEmpty()) {
						log.info("id Utente non valido");
					}
					List<Prenotazione> prenotazioniUt = prenotazioneService.getPrenotazioniByUtente(id);
					if (prenotazioniUt.isEmpty()) {
						log.info("Questo utente non ha prenotato alcuna postazione");
					}
					prenotazioniUt.forEach(prenotazione -> System.out.println(
						"Prenotazione ID: " + prenotazione.getId() + " | dal giorno: " + prenotazione.getData() + " fino al: " + prenotazione.getDataScadenza() + "\n" +
							"Username: " + prenotazione.getUtente().getUsername() + "Username ID" + prenotazione.getUtente().getId() + "\n" +
							"Postazione descrizione: " + prenotazione.getPostazione().getDescrizione() +
							" | Tipo: " + prenotazione.getPostazione().getTipo() +
							"\nEdificio: " + prenotazione.getPostazione().getEdificio().getNome() + " | " + prenotazione.getPostazione().getEdificio().getCity()
					));
					break;
				case 3:
					System.out.println("Inserisci ID utente:");
					Long utenteId = scanner.nextLong();
					System.out.println("Inserisci ID postazione:");
					Long postazioneId = scanner.nextLong();
					System.out.println("Inserisci data (YYYY-MM-DD):");
					String dataInput = scanner.next();

					try {
						LocalDate data = LocalDate.parse(dataInput);


						Optional<Utente> utenteOpt = utenteRepository.findById(utenteId);
						Optional<Postazione> postazioneOpt = postazioneRepository.findById(postazioneId);

						if (utenteOpt.isEmpty() || postazioneOpt.isEmpty()) {
							System.out.println("Errore: Utente o postazione non trovati.");
							return;
						}

						Prenotazione prenotazione = new Prenotazione(utenteOpt.get(), postazioneOpt.get(), data);
						prenotazioneService.savePrenotazione(prenotazione);
						System.out.println("Prenotazione effettuata con successo! Per vederla usa metodo 1 o 2");

					} catch (DateTimeParseException e) {
						System.out.println("Errore: Formato data non valido. Usa YYYY-MM-DD.");
					} catch (IllegalStateException e) {
						System.out.println("Errore: " + e.getMessage());
					}
					break;
			}

		}
	}


}
