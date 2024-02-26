package com.dataprev.abono.models;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_arquivo")
public class Arquivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String tipo;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] arquivoData;

    public Arquivo(Long id, String nome, String tipo, byte[] arquivoData) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.arquivoData = arquivoData;
    }

    public Arquivo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public byte[] getArquivoData() {
        return arquivoData;
    }

    public void setArquivoData(byte[] arquivoData) {
        this.arquivoData = arquivoData;
    }
}
