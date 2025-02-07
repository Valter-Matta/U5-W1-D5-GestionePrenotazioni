package com.gestione.prenotazioni.repo;


import com.gestione.prenotazioni.entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtenteRepository extends JpaRepository<Utente, Long> {
	Utente findByUsername(String username);
	Utente findByEmail (String email);
	Utente findByNomeCompleto(String nomeCompleto);

}
