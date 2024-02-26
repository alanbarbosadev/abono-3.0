package com.dataprev.abono.repositories.criterias;

public class PagamentoCriteria {
    private String anoBase;

    public PagamentoCriteria(String anoBase) {
        this.anoBase = anoBase;
    }

    public PagamentoCriteria() {
    }

    public String getAnoBase() {
        return anoBase;
    }

    public void setAnoBase(String anoBase) {
        this.anoBase = anoBase;
    }
}
