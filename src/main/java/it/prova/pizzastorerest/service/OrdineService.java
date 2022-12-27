package it.prova.pizzastorerest.service;

import it.prova.pizzastorerest.model.Ordine;

import java.util.List;

public interface OrdineService {

    public List<Ordine> listAllOrdini();

    public Ordine caricaSingoloOrdine(Long id);

    public Ordine caricaSingoloOrdineEager(Long id);

    public Ordine aggiorna(Ordine ordineInstance);

    public Ordine inserisciNuovo(Ordine ordineInstance);

    public void rimuovi(Long idToRemove);

    public List<Ordine> findByExample(Ordine example);
}
