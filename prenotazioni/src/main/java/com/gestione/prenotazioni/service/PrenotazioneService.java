package com.gestione.prenotazioni.service;

import com.gestione.prenotazioni.entity.Prenotazione;
import com.gestione.prenotazioni.repo.PrenotazioneRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.List;

@Service
@Validated
@RequiredArgsConstructor
public class PrenotazioneService {
	@Autowired
	private PrenotazioneRepository prenotazioneRepository;

	@Transactional
	public List<Prenotazione> getAllPrenotazioni () {
		return prenotazioneRepository.findAll();
	}

	public Prenotazione savePrenotazione (Prenotazione prenotazione) {
		if (!isPostazioneLibera(prenotazione.getPostazione().getId(), prenotazione.getData())) {
			throw new IllegalStateException("La postazione non è disponibile per questa data.");
		}
		if (hasExistingBooking(prenotazione.getUtente().getId(), prenotazione.getData())) {
			throw new IllegalStateException("un'utente ha già una prenotazione per questa data.");
		}
		return prenotazioneRepository.save(prenotazione);
	}

	public List<Prenotazione> getPrenotazioniByPostazioneAndData (Long postazioneId, LocalDate data) {
		return prenotazioneRepository.findByPostazioneIdAndData(postazioneId, data);
	}

	public List<Prenotazione> getPrenotazioniByUtenteAndData (Long utenteId, LocalDate data) {
		return prenotazioneRepository.findByUtenteIdAndData(utenteId, data);
	}

	public List<Prenotazione> getPrenotazioniByUtente (Long utenteId) {
		return prenotazioneRepository.findByUtenteId(utenteId);
	}

	private boolean isPostazioneLibera (Long postazioneId, LocalDate data) {
		return prenotazioneRepository.findByPostazioneIdAndData(postazioneId, data).isEmpty();
	}


	private boolean hasExistingBooking (Long utenteId, LocalDate data) {
		return !prenotazioneRepository.findByUtenteIdAndData(utenteId, data).isEmpty();
	}
}
