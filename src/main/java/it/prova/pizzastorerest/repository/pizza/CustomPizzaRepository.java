package it.prova.pizzastorerest.repository.pizza;

import java.util.List;

import it.prova.pizzastorerest.model.Pizza;

public interface CustomPizzaRepository {
	List<Pizza> findByExample(Pizza pizzaEsempio);
}
