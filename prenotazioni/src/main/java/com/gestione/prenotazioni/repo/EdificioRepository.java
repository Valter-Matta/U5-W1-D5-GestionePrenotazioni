package com.gestione.prenotazioni.repo;


import com.gestione.prenotazioni.entity.Edificio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EdificioRepository extends JpaRepository<Edificio, Long> {
	Edificio findByNome (String nome);
	Edificio findByIndirizzo (String indirizzo);
	Edificio findByCity (String city);
}
