package it.prova.pizzastorerest.service;

import it.prova.pizzastorerest.model.Ordine;
import it.prova.pizzastorerest.repository.ordine.OrdineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

}
