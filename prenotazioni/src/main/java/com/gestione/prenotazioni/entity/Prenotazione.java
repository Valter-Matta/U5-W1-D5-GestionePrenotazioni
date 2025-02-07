package com.gestione.prenotazioni.entity;

import com.gestione.prenotazioni.repo.PostazioneRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@Table (name = "prenotazione")
public class Prenotazione {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn (name = "utente_id", nullable = false)
	private Utente utente;

	@ManyToOne
	@JoinColumn (name = "postazione_id", nullable = false)
	private Postazione postazione;

	private LocalDate data;
	private LocalDate dataScadenza;

	public Prenotazione (Utente utente, Postazione postazione, LocalDate data) {
		this.utente = utente;
		this.postazione = postazione;
		this.data = data;
		this.dataScadenza = data.plusDays(1);
	}

}