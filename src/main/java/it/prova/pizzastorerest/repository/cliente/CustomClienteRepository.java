package it.prova.pizzastorerest.repository.cliente;

import java.util.List;

import it.prova.pizzastorerest.model.Cliente;

public interface CustomClienteRepository {
    List<Cliente> findByExample(Cliente clienteEsempio);
}
