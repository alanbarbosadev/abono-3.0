package com.dataprev.abono.repositories;

import com.dataprev.abono.models.Pagamento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PagamentoCriteriaRepository {
    @Autowired
    private EntityManager entityManager;

    public List<Pagamento> findAllPagamentosWithCriteria(Long codigoPagamento) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Pagamento> criteriaQuery = criteriaBuilder.createQuery(Pagamento.class);

        //select * from pagamentos
        Root<Pagamento> root = criteriaQuery.from(Pagamento.class);

        //where
        Predicate codigoPagamentoPredicate = criteriaBuilder.equal(root.get("codigoPagamento"), codigoPagamento);

        //query final ==> select * from tb_pagamentos where ano_base = anoBase
        criteriaQuery.where(codigoPagamentoPredicate);

        TypedQuery<Pagamento> query = entityManager.createQuery(criteriaQuery);

        return query.getResultList();
    }
}
