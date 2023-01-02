package it.prova.pizzastorerest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.pizzastorerest.model.Utente;

public interface UtenteRepository extends CrudRepository<Utente, Long>{
	
	@EntityGraph(attributePaths = { "ruoli" })
	Optional<Utente> findByUsername(String username);
	
	@Query("from Utente u left join fetch u.ruoli where u.id = ?1")
	Optional<Utente> findByIdConRuoli(Long id);
	
	@EntityGraph(attributePaths = { "ruoli" })
	Utente findByUsernameAndPassword(String username, String password);

	@Query("from Utente u left join fetch u.ruoli r where r.codice = 'ROLE_FATTORINO'")
	List<Utente> findAllUtentiFattorini();
}
