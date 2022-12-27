package it.prova.pizzastorerest.repository.cliente;

import org.springframework.data.repository.CrudRepository;

import it.prova.pizzastorerest.model.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente,Long>, CustomClienteRepository{
    Cliente findByNomeAndCognome(String nome, String cognome);
}
