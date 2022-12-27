package it.prova.pizzastorerest.repository.ordine;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.pizzastorerest.model.Ordine;

public interface OrdineRepository extends CrudRepository<Ordine, Long>, CustomOrdineRepository {
	
	Optional<Ordine> findById(Long id);
	
    @Query("from Ordine o left join fetch o.pizze left join fetch o.fattorino left join fetch o.cliente where o.id = ?1 ")
    Optional<Ordine> findByIdEager(Long id);
}
