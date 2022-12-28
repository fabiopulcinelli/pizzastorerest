package it.prova.pizzastorerest.repository.pizza;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.pizzastorerest.model.Pizza;

public interface PizzaRepository extends CrudRepository<Pizza, Long>, CustomPizzaRepository{
	
	Pizza findByDescrizione(String descrizione);
	
	@Query("from Pizza p where p.attivo is true ")
	List<Pizza> findAllPizzasDisponibili();

}
