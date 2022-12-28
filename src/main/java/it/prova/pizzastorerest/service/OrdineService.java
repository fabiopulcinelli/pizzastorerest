package it.prova.pizzastorerest.service;

import java.util.List;

import it.prova.pizzastorerest.model.Ordine;

public interface OrdineService {

    public List<Ordine> listAllOrdini();

    public Ordine caricaSingoloOrdine(Long id);

    public Ordine caricaSingoloOrdineEager(Long id);

    public Ordine aggiorna(Ordine ordineInstance);

    public Ordine inserisciNuovo(Ordine ordineInstance);

    public void rimuovi(Long idToRemove);

    public List<Ordine> findByExample(Ordine example);
    
    public Ordine findByCodice(String codice);
}
