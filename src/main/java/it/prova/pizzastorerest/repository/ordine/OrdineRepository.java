package it.prova.pizzastorerest.repository.ordine;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.pizzastorerest.model.Cliente;
import it.prova.pizzastorerest.model.Ordine;

public interface OrdineRepository extends CrudRepository<Ordine, Long>, CustomOrdineRepository {
	
	Ordine findByCodice(String codice);
	
	Optional<Ordine> findById(Long id);
	
    @Query("from Ordine o left join fetch o.pizze left join fetch o.fattorino left join fetch o.cliente where o.id = ?1 ")
    Optional<Ordine> findByIdEager(Long id);
    
    @Query(value= "select sum(p.prezzoBase) from pizza p join ordine_pizza op on p.id=op.pizza_id where ordine_id = ?1", nativeQuery = true)
	Integer calcolaSommaPrezzi(Long id);
	
	@Query("select sum(o.costoTotale) from Ordine o where o.data between ?1 and ?2")
	Integer calcolaRicaviTotaliTra(LocalDate dataInizio, LocalDate dataFine);
	
	Integer countByDataBetween(LocalDate dataInizio, LocalDate dataFine);
	
	@Query(value = "select count(op.pizza_id) from ordine_pizza op join ordine o on o.id = op.ordine_id where o.data between ?1 and ?2", nativeQuery = true)
	Integer countPizzeOrderedBetween(LocalDate dataInizio, LocalDate dataFine);
	
	@Query("select distinct c from Ordine o join o.cliente c where c is not null and o.costoTotale > 100 and o.data between ?1 and ?2")
	List<Cliente> findAllClientiVirtuosiBetween(LocalDate dataInizio, LocalDate dataFine);
    
    @Query("from Ordine o where o.closed = false")
	List<Ordine> findAllOrdiniPerFattorino();
}
