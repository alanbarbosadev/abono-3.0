package com.dataprev.abono.repositories.criterias;

import java.util.Date;

public class PagamentoCriteria {
    private String anoBase;
    private Date inicioIntervalo;
    private Date fimInvervalo;

    public PagamentoCriteria(String anoBase, Date inicioIntervalo, Date fimInvervalo) {
        this.anoBase = anoBase;
        this.inicioIntervalo = inicioIntervalo;
        this.fimInvervalo = fimInvervalo;
    }

    public PagamentoCriteria() {
    }

    public String getAnoBase() {
        return anoBase;
    }

    public void setAnoBase(String anoBase) {
        this.anoBase = anoBase;
    }

    public Date getInicioIntervalo() {
        return inicioIntervalo;
    }

    public void setInicioIntervalo(Date inicioIntervalo) {
        this.inicioIntervalo = inicioIntervalo;
    }

    public Date getFimInvervalo() {
        return fimInvervalo;
    }

    public void setFimInvervalo(Date fimInvervalo) {
        this.fimInvervalo = fimInvervalo;
    }
}
