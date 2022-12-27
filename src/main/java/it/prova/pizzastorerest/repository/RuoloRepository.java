package it.prova.pizzastorerest.repository;

import org.springframework.data.repository.CrudRepository;

import it.prova.pizzastorerest.model.Ruolo;

public interface RuoloRepository extends CrudRepository<Ruolo, Long> {

	Ruolo findByDescrizioneAndCodice(String descrizione, String codice);
}
