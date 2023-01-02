package it.prova.pizzastorerest.service;

import java.util.List;

import it.prova.pizzastorerest.model.Utente;

public interface UtenteService {

    public List<Utente> listAllUtenti();
    
    public List<Utente> listAllFattorini();

    public Utente caricaSingoloUtente(Long id);

    public Utente caricaSingoloUtenteConRuoli(Long id);

    public void aggiorna(Utente utenteInstance);

    public void inserisciNuovo(Utente utenteInstance);

    public void rimuovi(Long idToRemove);

    public List<Utente> findByExample(Utente example);

    public Utente findByUsernameAndPassword(String username, String password);

    public void changeUserAbilitation(Long utenteInstanceId);

    public Utente findByUsername(String username);

}
