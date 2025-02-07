package com.gestione.prenotazioni.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "postazione")
public class Postazione {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;

	private String descrizione;

	@Enumerated (EnumType.STRING)
	private Stato tipo;
	private int numeroMassimoOccupanti;

	@ManyToOne
	@JoinColumn (name = "edificio_id")
	private Edificio edificio;

	@ManyToMany (fetch = FetchType.EAGER)
	private List<Utente> utenti;

	public enum Stato {
		PRIVATO, OPENSPACE, SALA_RIUNIONI
	}
}