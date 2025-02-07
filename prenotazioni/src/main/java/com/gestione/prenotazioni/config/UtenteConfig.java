package com.gestione.prenotazioni.config;

import com.gestione.prenotazioni.entity.Utente;
import com.gestione.prenotazioni.repo.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class UtenteConfig {
	@Autowired
	FakerConfig faker;


	@Bean
	@Scope("prototype")
	public Utente utente () {
		Utente u = new Utente();
		u.setEmail(faker.faker().internet().emailAddress());
		u.setNomeCompleto(faker.faker().name().fullName());
		u.setUsername(faker.faker().leagueOfLegends().champion());
		return u;
	}

}
