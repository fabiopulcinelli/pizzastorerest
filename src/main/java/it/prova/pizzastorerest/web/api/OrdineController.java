package it.prova.pizzastorerest.web.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.prova.pizzastorerest.dto.ClienteDTO;
import it.prova.pizzastorerest.dto.OrdineDTO;
import it.prova.pizzastorerest.dto.StatisticheVarieOrdiniDTO;
import it.prova.pizzastorerest.model.Ordine;
import it.prova.pizzastorerest.service.OrdineService;
import it.prova.pizzastorerest.web.api.exception.IdNotNullForInsertException;
import it.prova.pizzastorerest.web.api.exception.NotFoundException;

@RestController
@RequestMapping("/api/ordine")
public class OrdineController {

    //TODO Implement OrdineController
    @Autowired
    private OrdineService ordineService;

    @GetMapping
    public List<OrdineDTO> getAll() {
        return OrdineDTO.createOrdineDTOListFromModelList(ordineService.listAllOrdini());
    }

    @PostMapping
    public OrdineDTO createNew(@Valid @RequestBody OrdineDTO ordineInput){

        if(ordineInput.getId()!=null)
            throw new IdNotNullForInsertException("Non è ammesso fornire un id per la creazione");

        Ordine ordineInserito = ordineService.inserisciNuovo(ordineInput.buildOrdineModel());
        return  OrdineDTO.buildOrdineDTOFromModel(ordineInserito, true, true, true);

    }

    @GetMapping("/{id}")
    public OrdineDTO findById(@PathVariable(value = "id", required = true) long id){
        Ordine ordine = ordineService.caricaSingoloOrdine(id);
        if(ordine == null) throw new NotFoundException("Ordine not found con id: " +id);
        return OrdineDTO.buildOrdineDTOFromModel(ordine, true, true, true);
    }

    @PutMapping("/{id}")
    public OrdineDTO update(@Valid @RequestBody OrdineDTO ordineInput, @PathVariable(value = "id", required = true) long id){
        Ordine ordine = ordineService.caricaSingoloOrdine(id);
        if(ordine == null) throw new NotFoundException("Ordine not found con id: " +id);
        ordineInput.setId(id);
        Ordine ordineAggiornato = ordineService.aggiorna(ordineInput.buildOrdineModel());
        return OrdineDTO.buildOrdineDTOFromModel(ordine, true, true, true);
    }

    //sarebbe chiudi ordine
    @DeleteMapping("/{id}")
    public void delete(@PathVariable(value = "id", required = true) long id) {
        Ordine ordine = ordineService.caricaSingoloOrdine(id);
        if (ordine == null)
            throw new NotFoundException("Ordine not found con id: " + id);
        ordineService.rimuovi(id);
    }

    @PostMapping("/search")
    public List<OrdineDTO> search(@RequestBody OrdineDTO example) {
        return OrdineDTO.createOrdineDTOListFromModelList(ordineService.findByExample(example.buildOrdineModel()));
    }
    
    @GetMapping("changeAbilitation/{id}")
	public void changeAbilitation(@PathVariable(value = "id", required = true) long id) {
		ordineService.changeAbilitation(id);
	}
    
    @GetMapping("/calcolaPrezzoOrdine/{id}")
    public Integer calcolaPrezzoOrdine(@PathVariable(value = "id", required = true) long id) {
    	return ordineService.calcolaPrezzoOrdine(id);
	}
    
    @PostMapping("/ricaviTotaliBetween")
	public Integer ricaviTotaliBetween(@Valid @RequestBody StatisticheVarieOrdiniDTO dateInput) {
		return ordineService.ricaviTotaliBetween(dateInput.getDataInizio(), dateInput.getDataFine());
	}

	@PostMapping("/ordiniTotaliBetween")
	public Integer ordiniTotaliBetween(@Valid @RequestBody StatisticheVarieOrdiniDTO dateInput) {
		return ordineService.ordiniTotaliBetween(dateInput.getDataInizio(), dateInput.getDataFine());
	}

	@PostMapping("/pizzeTotaliOrderedBetween")
	public Integer pizzeTotaliOrderedBetween(@Valid @RequestBody StatisticheVarieOrdiniDTO dateInput) {
		return ordineService.pizzeOrdinateBetween(dateInput.getDataInizio(), dateInput.getDataFine());
	}

	@PostMapping("/clientiVirtuosiWithOrdineBetween")
	public List<ClienteDTO> clientiVirtuosiWithOrdineBetween(@Valid @RequestBody StatisticheVarieOrdiniDTO dateInput) {
		return ClienteDTO.createClienteDTOListFromModelList(
				ordineService.clientiVirtuosiBetween(dateInput.getDataInizio(), dateInput.getDataFine()));
	}

    @GetMapping("/fattorino")
	public List<OrdineDTO> ordiniPerFattorino() {
		return OrdineDTO.createOrdineDTOListFromModelList(ordineService.ordiniPerFattorino());
	}
}
