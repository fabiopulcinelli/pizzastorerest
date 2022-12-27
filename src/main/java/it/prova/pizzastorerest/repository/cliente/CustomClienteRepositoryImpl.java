package it.prova.pizzastorerest.repository.cliente;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import it.prova.pizzastorerest.model.Cliente;

public class CustomClienteRepositoryImpl implements CustomClienteRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Cliente> findByExample(Cliente clienteEsempio) {
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder("select c from Cliente c where c.id = c.id ");

		if (StringUtils.isNotEmpty(clienteEsempio.getNome())) {
			whereClauses.add(" c.nome  like :nome ");
			paramaterMap.put("nome", "%" + clienteEsempio.getNome() + "%");
		}
		if (StringUtils.isNotEmpty(clienteEsempio.getCognome())) {
			whereClauses.add(" c.cognome like :cognome ");
			paramaterMap.put("cognome", "%" + clienteEsempio.getCognome() + "%");
		}
		if (StringUtils.isNotEmpty(clienteEsempio.getIndirizzo())) {
			whereClauses.add(" c.indirizzo like :indirizzo ");
			paramaterMap.put("indirizzo", "%" + clienteEsempio.getIndirizzo() + "%");
		}
		if (clienteEsempio.getAttivo() != null) {
			whereClauses.add(" c.attivo =:attivo ");
			paramaterMap.put("attivo", clienteEsempio.getAttivo());
		}

		queryBuilder.append(!whereClauses.isEmpty() ? " and " : "");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Cliente> typedQuery = entityManager.createQuery(queryBuilder.toString(), Cliente.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();
	}

}
