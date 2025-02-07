package com.gestione.prenotazioni.config;

import com.gestione.prenotazioni.entity.Edificio;
import com.gestione.prenotazioni.repo.EdificioRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class EdificioConfig {


	@Bean
	public Edificio BancaSanPaolo () {
		Edificio e = new Edificio();
		e.setNome("Intesa San Paolo s.p.a");
		e.setIndirizzo("Via Fratelli Cervino, 9");
		e.setCity("Torino");
		return e;
	}

	@Bean
	public Edificio Lavazza () {
		Edificio e = new Edificio();
		e.setNome("Lavazza");
		e.setIndirizzo("Corso Francia, 11");
		e.setCity("Asti");
		return e;
	}
}
