package it.prova.pizzastorerest.repository.pizza;

import org.springframework.data.repository.CrudRepository;

import it.prova.pizzastorerest.model.Pizza;

public interface PizzaRepository extends CrudRepository<Pizza, Long>, CustomPizzaRepository{
	
	Pizza findByDescrizione(String descrizione);

}
