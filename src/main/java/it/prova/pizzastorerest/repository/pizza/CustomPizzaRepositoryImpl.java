package it.prova.pizzastorerest.repository.pizza;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.prova.pizzastorerest.model.Pizza;

public class CustomPizzaRepositoryImpl implements CustomPizzaRepository{
	@PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Pizza> findByExample(Pizza pizzaEsempio) {
        Map<String, Object> paramaterMap = new HashMap<String, Object>();
        List<String> whereClauses = new ArrayList<String>();

        StringBuilder queryBuilder = new StringBuilder("select p from Pizza p where p.id = p.id ");

        if (StringUtils.isNotEmpty(pizzaEsempio.getDescrizione())) {
            whereClauses.add(" p.descrizione  like :descrizione ");
            paramaterMap.put("descrizione", "%" + pizzaEsempio.getDescrizione() + "%");
        }
        if (StringUtils.isNotEmpty(pizzaEsempio.getIngredienti())) {
            whereClauses.add(" p.ingredienti like :ingredienti ");
            paramaterMap.put("ingredienti", "%" + pizzaEsempio.getIngredienti() + "%");
        }
        if (pizzaEsempio.getPrezzoBase() != null) {
            whereClauses.add(" p.prezzoBase >= :prezzoBase ");
            paramaterMap.put("prezzoBase", pizzaEsempio.getPrezzoBase());
        }

        queryBuilder.append(!whereClauses.isEmpty() ? " and " : "");
        queryBuilder.append(StringUtils.join(whereClauses, " and "));
        TypedQuery<Pizza> typedQuery = entityManager.createQuery(queryBuilder.toString(), Pizza.class);

        for (String key : paramaterMap.keySet()) {
            typedQuery.setParameter(key, paramaterMap.get(key));
        }

        return typedQuery.getResultList();
    }
}
