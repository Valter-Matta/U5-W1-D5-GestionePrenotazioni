package com.gestione.prenotazioni.runners;


import com.gestione.prenotazioni.config.EdificioConfig;
import com.gestione.prenotazioni.config.PostazioneConfig;
import com.gestione.prenotazioni.config.UtenteConfig;
import com.gestione.prenotazioni.entity.Edificio;
import com.gestione.prenotazioni.entity.Postazione;
import com.gestione.prenotazioni.entity.Prenotazione;
import com.gestione.prenotazioni.entity.Utente;
import com.gestione.prenotazioni.repo.EdificioRepository;
import com.gestione.prenotazioni.repo.PostazioneRepository;
import com.gestione.prenotazioni.repo.PrenotazioneRepository;
import com.gestione.prenotazioni.repo.UtenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
@Order (1)
public class CreateDBRunner implements CommandLineRunner {
	//config
	private final EdificioRepository edificioRepository;
	private final UtenteRepository utenteRepository;
	private final PostazioneRepository postazioneRepository;
	private final PrenotazioneRepository prenotazioneRepository;
	//bean
	private final Edificio BancaSanPaolo;
	private final Edificio Lavazza;
	private final Postazione IT;
	private final Postazione Hall;
	private final Postazione Receptionist;
	private final Postazione Office;


	private final ApplicationContext context;


	@Override
	public void run (String... args) throws Exception {

		//creo utenti
		for (int i = 0; i < 10; i++) {
			Utente nuovoUtente = context.getBean(Utente.class); // Crea un nuovo utente
			utenteRepository.save(nuovoUtente);
		}

		//creo edifici
		edificioRepository.save(BancaSanPaolo);
		edificioRepository.save(Lavazza);

		//creo postazioni
		postazioneRepository.save(Receptionist);
		postazioneRepository.save(Office);
		postazioneRepository.save(IT);
		postazioneRepository.save(Hall);


		//prendo 2 utenti
		Utente ut = utenteRepository.findById(1L).orElse(null);
		Utente ut2 = utenteRepository.findById(2L).orElse(null);

		//creo un paio di prenotazioni
		Prenotazione prenotazione1 = new Prenotazione();
		prenotazione1.setUtente(ut);
		prenotazione1.setPostazione(Receptionist);
		prenotazione1.setData(LocalDate.of(2025, 7, 2));
		prenotazione1.setDataScadenza(LocalDate.of(2025, 7, 2).plusDays(1));
		prenotazioneRepository.save(prenotazione1);

		Prenotazione prenotazione2 = new Prenotazione();
		prenotazione2.setUtente(ut);
		prenotazione2.setPostazione(Hall);
		prenotazione2.setData(LocalDate.of(2025, 2, 7));
		prenotazione2.setDataScadenza(LocalDate.of(2025, 2, 7).plusDays(1));
		prenotazioneRepository.save(prenotazione2);

	}


}
