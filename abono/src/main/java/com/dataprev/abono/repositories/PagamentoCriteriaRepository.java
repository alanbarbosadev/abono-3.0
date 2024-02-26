package com.dataprev.abono.repositories;

import com.dataprev.abono.models.Pagamento;
import com.dataprev.abono.repositories.criterias.PagamentoCriteria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class PagamentoCriteriaRepository {
    @Autowired
    private EntityManager entityManager;

    public List<Pagamento> findAllPagamentosWithCriteria(String anoBase, Date inicioIntervalo, Date fimInvervalo) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Pagamento> criteriaQuery = criteriaBuilder.createQuery(Pagamento.class);
        List<Predicate> predicates = new ArrayList<>();

        //select * from pagamentos
        Root<Pagamento> root = criteriaQuery.from(Pagamento.class);

        //where
        if (anoBase != null) {
            Predicate anoBasePredicate = criteriaBuilder.like(root.get("anoBase"), anoBase);
            predicates.add(anoBasePredicate);
        }

        if (inicioIntervalo != null && fimInvervalo != null) {
            Predicate intervaloPredicate = criteriaBuilder.between(root.get("trabalhador").get("nascimento"), inicioIntervalo, fimInvervalo);
            predicates.add(intervaloPredicate);
        }



        Predicate queryFinal = criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        //query final ==> select * from tb_pagamentos where ano_base = anoBase
        criteriaQuery.where(queryFinal);

        TypedQuery<Pagamento> query = entityManager.createQuery(criteriaQuery);

        return query.getResultList();
    }
}
