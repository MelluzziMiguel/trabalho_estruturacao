package com.umfg.clinicaveterinaria.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Consulta {

    private int id;
    private int idAnimal;
    private LocalDate dataAtendimento;
    private String motivo;
    private BigDecimal valor;

    public Consulta() {
    }

    public Consulta(int idAnimal, LocalDate dataAtendimento, String motivo, BigDecimal valor) {
        this.idAnimal = idAnimal;
        this.dataAtendimento = dataAtendimento;
        this.motivo = motivo;
        this.valor = valor;
    }

    public Consulta(int id, int idAnimal, LocalDate dataAtendimento, String motivo, BigDecimal valor) {
        this.id = id;
        this.idAnimal = idAnimal;
        this.dataAtendimento = dataAtendimento;
        this.motivo = motivo;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public LocalDate getDataAtendimento() {
        return dataAtendimento;
    }

    public void setDataAtendimento(LocalDate dataAtendimento) {
        this.dataAtendimento = dataAtendimento;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Consulta{" +
                "id=" + id +
                ", idAnimal=" + idAnimal +
                ", dataAtendimento=" + dataAtendimento +
                ", motivo='" + motivo + '\'' +
                ", valor=" + valor +
                '}';
    }
}