package it.prova.pizzastorerest.service;

import it.prova.pizzastorerest.model.Pizza;

import java.util.List;

public interface PizzaService {

    public List<Pizza> listAllPizzas();

    public Pizza caricaSingolaPizza(Long id);

    public Pizza aggiorna(Pizza pizzaInstance);

    public Pizza inserisciNuovo(Pizza pizzaInstance);

    public void rimuovi(Long idToRemove);

    public List<Pizza> findByExample(Pizza example);

    public Pizza findByDescrizione(String descrizione);
}
