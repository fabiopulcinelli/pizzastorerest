package it.prova.pizzastorerest.repository.ordine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import it.prova.pizzastorerest.model.Ordine;

public class CustomOrdineRepositoryImpl implements CustomOrdineRepository {

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Ordine> findByExample(Ordine ordineEsempio) {
        Map<String, Object> paramaterMap = new HashMap<String, Object>();
        List<String> whereClauses = new ArrayList<String>();

        StringBuilder queryBuilder = new StringBuilder("select o from Ordine o where o.id = o.id ");

        if (ordineEsempio.getData() != null) {
            whereClauses.add(" o.data  >= :data ");
            paramaterMap.put("data", ordineEsempio.getData());
        }
        if (ordineEsempio.getClosed() != null) {
            whereClauses.add(" o.closed = :closed ");
            paramaterMap.put("closed",ordineEsempio.getClosed());
        }
        if (StringUtils.isNotEmpty(ordineEsempio.getCodice())) {
            whereClauses.add(" o.codice like :codice ");
            paramaterMap.put("codice", "%" + ordineEsempio.getCodice() + "%");
        }
        if (ordineEsempio.getCliente() != null) {
            whereClauses.add(" o.cliente.id =:idCliente ");
            paramaterMap.put("idCliente", ordineEsempio.getCliente().getId());
        }
        if (ordineEsempio.getFattorino() != null) {
            whereClauses.add(" o.fattorino.id =:idFattorino ");
            paramaterMap.put("idFattorino", ordineEsempio.getFattorino().getId());
        }

        queryBuilder.append(!whereClauses.isEmpty() ? " and " : "");
        queryBuilder.append(StringUtils.join(whereClauses, " and "));
        TypedQuery<Ordine> typedQuery = entityManager.createQuery(queryBuilder.toString(), Ordine.class);

        for (String key : paramaterMap.keySet()) {
            typedQuery.setParameter(key, paramaterMap.get(key));
        }

        return typedQuery.getResultList();
    }
}
