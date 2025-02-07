package com.gestione.prenotazioni.repo;


import com.gestione.prenotazioni.entity.Edificio;
import com.gestione.prenotazioni.entity.Postazione;
import com.gestione.prenotazioni.entity.Utente;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostazioneRepository extends JpaRepository<Postazione, Long> {

	Postazione findByDescrizione(String descrizione);

	Postazione findByNumeroMassimoOccupanti(int numeroMassimoOccupanti);
	Postazione findByEdificio(Edificio edificio);
	Postazione findByUtenti(List<Utente> utenti);
}
