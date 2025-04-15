package br.com.fiap.finance_walk_api.repository.specification;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import br.com.fiap.finance_walk_api.model.Transaction;
import br.com.fiap.finance_walk_api.model.TransactionFilter;

public class TransactionSpecification {
    //transações por publico
    //Clean Code - Não se pode ter mais de 3 parametros no mesmo metodo
    public static Specification<Transaction> withFilters(TransactionFilter filter){

        /*
            Sempre será esses 3 por convenção
         * root - registros no BD
         * query - a consulta em si
         * cb - obj auxiliar construtor de critério
         * 
        */
        return (root, query, cb) -> {
            //Predicado - consulta com toda suas regras
            List<Predicate> predicates = new ArrayList<>();

            if(filter.description() != null){
                predicates.add(
                    cb.like(cb.lower(root.get("description")), "%" + filter.description() + "%")
                );
            }

            if (filter.startDate() != null && filter.endDate() != null) {
                predicates.add(
                    cb.between(root.get("date"), filter.startDate(), filter.endDate())
                );
            }

            if (filter.startDate() != null) {
                predicates.add(cb.equal(root.get("date"), filter.startDate()));
            }


            var listPredicates = predicates.toArray(new Predicate[0]); 

            return cb.and(listPredicates);
   
        }; //retorna um método - como será filtrado o objeto
        
        
        

    }
}
