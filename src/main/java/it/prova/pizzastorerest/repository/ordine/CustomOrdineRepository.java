package it.prova.pizzastorerest.repository.ordine;

import java.util.List;

import it.prova.pizzastorerest.model.Ordine;

public interface CustomOrdineRepository {

    List<Ordine> findByExample(Ordine ordineEsempio);
}
