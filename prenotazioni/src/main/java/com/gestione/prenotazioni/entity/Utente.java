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
@Table (name = "utente")
public class Utente {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;

	//String Username String nomeCompleto	String email	List<Postazione> postazioni
	private String username;
	private String nomeCompleto;
	private String email;
	@ManyToMany (fetch = FetchType.EAGER)
	private List<Postazione> postazioni;

	@Override
	public String toString () {
		return "Utente{id=" + id + ", nome=" + nomeCompleto + ", email=" + email + "Username" + username + "}";
	}
}