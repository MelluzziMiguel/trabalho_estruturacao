package model;

import java.math.BigDecimal;

public class OrdemServico {

    private Long id;
    private Long idVeiculo;
    private String descricao;
    private BigDecimal valor;
    private String status;

    public OrdemServico() {
    }

    public OrdemServico(Long idVeiculo,
                        String descricao,
                        BigDecimal valor,
                        String status) {

        this.idVeiculo = idVeiculo;
        this.descricao = descricao;
        this.valor = valor;
        this.status = status;
    }

    public OrdemServico(Long id,
                        Long idVeiculo,
                        String descricao,
                        BigDecimal valor,
                        String status) {

        this.id = id;
        this.idVeiculo = idVeiculo;
        this.descricao = descricao;
        this.valor = valor;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(Long idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}