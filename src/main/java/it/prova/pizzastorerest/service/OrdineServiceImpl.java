package it.prova.pizzastorerest.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.pizzastorerest.model.Cliente;
import it.prova.pizzastorerest.model.Ordine;
import it.prova.pizzastorerest.repository.ordine.OrdineRepository;
import it.prova.pizzastorerest.web.api.exception.NotFoundException;

@Service
@Transactional(readOnly = true)
public class OrdineServiceImpl implements OrdineService{

    @Autowired
    private OrdineRepository repository;

    @Override
    public List<Ordine> listAllOrdini() {
        return (List<Ordine>) repository.findAll();
    }

    @Override
    public Ordine caricaSingoloOrdine(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Ordine caricaSingoloOrdineEager(Long id) {
        return repository.findByIdEager(id).orElse(null);
    }

    @Transactional
    public Ordine aggiorna(Ordine ordineInstance) {
        Ordine ordineReloaded = repository.findById(ordineInstance.getId()).orElse(null);
        if(ordineReloaded == null)
            throw new RuntimeException("Elemento non trovato");
        ordineReloaded.setCliente(ordineInstance.getCliente());
        ordineReloaded.setPizze(ordineInstance.getPizze());
        ordineReloaded.setClosed(ordineInstance.getClosed());
        ordineReloaded.setCodice(ordineInstance.getCodice());
        ordineReloaded.setFattorino(ordineInstance.getFattorino());
        return repository.save(ordineReloaded);
    }

    @Transactional
    public Ordine inserisciNuovo(Ordine ordineInstance) {
        ordineInstance.setClosed(false);
        repository.save(ordineInstance);
        ordineInstance.setCostoTotale(calcolaPrezzoOrdine(ordineInstance.getId()));
		return repository.save(ordineInstance);
    }

    @Transactional
    public void rimuovi(Long idToRemove) {
        //Eliminazione logica
        Ordine ordineReloaded = repository.findById(idToRemove).orElse(null);
        if(ordineReloaded == null)
            throw new RuntimeException("Elemento non trovato");
        ordineReloaded.setClosed(true);
        repository.save(ordineReloaded);
    }

    @Override
    public List<Ordine> findByExample(Ordine example) {
        return repository.findByExample(example);
    }

    @Override
	@Transactional
	public void changeAbilitation(Long id) {
		Ordine ordineInstance = caricaSingoloOrdine(id);
		if (ordineInstance == null)
			throw new NotFoundException("Elemento non trovato.");

		if (ordineInstance.getClosed()) {
			ordineInstance.setClosed(false);
		} else {
			ordineInstance.setClosed(true);
		}

	}
    
	@Override
	public Ordine findByCodice(String codice) {
		return repository.findByCodice(codice);
	}
	
	@Override
	public Integer calcolaPrezzoOrdine(Long idOrdine) {
		return repository.calcolaSommaPrezzi(idOrdine);
	}

	@Override
	public Integer ricaviTotaliBetween(LocalDate dataInizio, LocalDate dataFine) {
		return repository.calcolaRicaviTotaliTra(dataInizio, dataFine);
	}

	@Override
	public Integer ordiniTotaliBetween(LocalDate dataInizio, LocalDate dataFine) {
		return repository.countByDataBetween(dataInizio, dataFine);
	}

	@Override
	public Integer pizzeOrdinateBetween(LocalDate dataInizio, LocalDate dataFine) {
		return repository.countPizzeOrderedBetween(dataInizio, dataFine);
	}

	@Override
	public List<Cliente> clientiVirtuosiBetween(LocalDate dataInizio, LocalDate dataFine) {
		return repository.findAllClientiVirtuosiBetween(dataInizio, dataFine);
	}

	@Override
	public List<Ordine> ordiniPerFattorino() {
		return repository.findAllOrdiniPerFattorino();
	}
}
