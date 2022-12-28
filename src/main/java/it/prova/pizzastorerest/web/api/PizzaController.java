package it.prova.pizzastorerest.web.api;

import it.prova.pizzastorerest.dto.PizzaDTO;
import it.prova.pizzastorerest.model.Pizza;
import it.prova.pizzastorerest.service.PizzaService;
import it.prova.pizzastorerest.web.api.exception.IdNotNullForInsertException;
import it.prova.pizzastorerest.web.api.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/pizza")
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;

    @GetMapping
    public List<PizzaDTO> getAll() {
        return PizzaDTO.createPizzaDTOListFromModelList(pizzaService.listAllPizzasDisponibili());
    }

    @PostMapping
    public PizzaDTO createNew(@Valid @RequestBody PizzaDTO pizzaInput) {
    	//custom exception in caso si passa id in input
        if (pizzaInput.getId() != null)
            throw new IdNotNullForInsertException("Non è ammesso fornire un id per la creazione");

        Pizza pizzaInserita = pizzaService.inserisciNuovo(pizzaInput.buildPizzaModel());
        return PizzaDTO.buildPizzaDTOFromModel(pizzaInserita);
    }

    @GetMapping("/{id}")
    public PizzaDTO findById(@PathVariable(value = "id", required = true) long id) {
        Pizza pizza = pizzaService.caricaSingolaPizza(id);

        if (pizza == null)
            throw new NotFoundException("Pizza not found con id: " + id);

        return PizzaDTO.buildPizzaDTOFromModel(pizza);
    }

    @PutMapping("/{id}")
    public PizzaDTO update(@Valid @RequestBody PizzaDTO pizzaInput, @PathVariable(required = true) Long id) {
        Pizza pizza = pizzaService.caricaSingolaPizza(id);

        if (pizza == null)
            throw new NotFoundException("Pizza not found con id: " + id);

        pizzaInput.setId(id);
        Pizza pizzaAggiornata = pizzaService.aggiorna(pizzaInput.buildPizzaModel());
        return PizzaDTO.buildPizzaDTOFromModel(pizzaAggiornata);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(value = "id", required = true) long id) {
        Pizza pizza = pizzaService.caricaSingolaPizza(id);
        if (pizza == null)
            throw new NotFoundException("Pizza not found con id: " + id);
        
        // cancellazione logica che mette il flag a false
        pizzaService.rimuovi(id);
    }

    @PostMapping("/search")
    public List<PizzaDTO> search(@RequestBody PizzaDTO example) {
        return PizzaDTO.createPizzaDTOListFromModelList(pizzaService.findByExample(example.buildPizzaModel()));
    }
}
