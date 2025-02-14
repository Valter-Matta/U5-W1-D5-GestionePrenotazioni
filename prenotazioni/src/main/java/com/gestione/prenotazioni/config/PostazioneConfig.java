package com.gestione.prenotazioni.config;

import com.gestione.prenotazioni.entity.Edificio;
import com.gestione.prenotazioni.entity.Postazione;
import com.gestione.prenotazioni.repo.PostazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class PostazioneConfig {
	@Autowired
	FakerConfig faker;
	@Autowired
	Edificio Lavazza;
	@Autowired
	Edificio BancaSanPaolo;


	@Bean
	@Scope ("prototype")
	public Postazione Receptionist () {
		Postazione p = new Postazione();
		p.setDescrizione(faker.faker().team().name());
		p.setTipo(Postazione.Stato.OPENSPACE);
		p.setNumeroMassimoOccupanti(1);
		p.setEdificio(Lavazza);
		return p;
	}

	@Bean
	@Scope ("prototype")
	public Postazione Hall () {
		Postazione p = new Postazione();
		p.setDescrizione(faker.faker().team().name());
		p.setTipo(Postazione.Stato.OPENSPACE);
		p.setNumeroMassimoOccupanti(1);
		p.setEdificio(Lavazza);
		return p;
	}

	@Bean
	@Scope ("prototype")
	public Postazione Office () {
		Postazione p = new Postazione();
		p.setDescrizione(faker.faker().team().name());
		p.setTipo(Postazione.Stato.PRIVATO);
		p.setNumeroMassimoOccupanti(2);
		p.setEdificio(BancaSanPaolo);
		return p;
	}
	@Bean
	@Scope ("prototype")
	public Postazione IT () {
		Postazione p = new Postazione();
		p.setDescrizione(faker.faker().team().name());
		p.setTipo(Postazione.Stato.SALA_RIUNIONI);
		p.setNumeroMassimoOccupanti(2);
		p.setEdificio(BancaSanPaolo);
		return p;
	}
}
