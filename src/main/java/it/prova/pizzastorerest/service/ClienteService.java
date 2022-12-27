package it.prova.pizzastorerest.service;

import it.prova.pizzastorerest.model.Cliente;

import java.util.List;

public interface ClienteService {

    public List<Cliente> listAllClienti();

    public Cliente caricaSingoloCliente(Long id);

    public Cliente aggiorna(Cliente clienteInstance);

    public Cliente inserisciNuovo(Cliente clienteInstance);

    public void rimuovi(Long idToRemove);
    public List<Cliente> findByExample(Cliente example);

    public Cliente findByNomeAndCognome(String nome, String cognome);
}
