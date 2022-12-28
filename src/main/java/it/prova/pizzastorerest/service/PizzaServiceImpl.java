package it.prova.pizzastorerest.service;

import it.prova.pizzastorerest.model.Pizza;
import it.prova.pizzastorerest.repository.pizza.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PizzaServiceImpl implements PizzaService {

    @Autowired
    private PizzaRepository repository;

    @Override
    public List<Pizza> listAllPizzas() {
        return (List<Pizza>) repository.findAll();
    }
    
    @Override
    public List<Pizza> listAllPizzasDisponibili() {
        return (List<Pizza>) repository.findAllPizzasDisponibili();
    }

    @Override
    public Pizza caricaSingolaPizza(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    public Pizza aggiorna(Pizza pizzaInstance) {
        Pizza pizzaReloaded = repository.findById(pizzaInstance.getId()).orElse(null);
        if(pizzaReloaded == null) throw new RuntimeException("Elemento non trovato");
        pizzaReloaded.setDescrizione(pizzaInstance.getDescrizione());
        pizzaReloaded.setIngredienti(pizzaInstance.getIngredienti());
        pizzaReloaded.setPrezzoBase(pizzaInstance.getPrezzoBase());
        pizzaReloaded.setAttivo(pizzaInstance.getAttivo());
        return repository.save(pizzaReloaded);
    }

    @Transactional
    public Pizza inserisciNuovo(Pizza pizzaInstance) {
        pizzaInstance.setAttivo(true);
        return repository.save(pizzaInstance);
    }

    @Transactional
    public void rimuovi(Long idToRemove) {
        //delete metterà solo il flag a false
        Pizza pizzaReloaded = repository.findById(idToRemove).orElse(null);
        if(pizzaReloaded == null) throw new RuntimeException("Elemento non trovato");
        pizzaReloaded.setAttivo(false);
        repository.save(pizzaReloaded);
    }

    @Override
    public List<Pizza> findByExample(Pizza example) {
        return repository.findByExample(example);
    }

    @Override
    public Pizza findByDescrizione(String descrizione) {
        return repository.findByDescrizione(descrizione);
    }

}
