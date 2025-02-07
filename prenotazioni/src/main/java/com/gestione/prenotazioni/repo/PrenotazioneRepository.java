package com.gestione.prenotazioni.repo;


import com.gestione.prenotazioni.entity.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
	List<Prenotazione> findByPostazioneIdAndData (Long postazioneId, LocalDate data);
	List<Prenotazione> findByUtenteIdAndData (Long utenteId, LocalDate data);
	List<Prenotazione> findByUtenteId (Long utenteId);

}
