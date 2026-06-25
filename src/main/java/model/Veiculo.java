package model;

public class Veiculo {

    private Long id;
    private String placa;
    private String modelo;
    private Integer ano;
    private Long idCliente;

    public Veiculo() {
    }

    public Veiculo(String placa, String modelo, Integer ano, Long idCliente) {
        this.placa = placa;
        this.modelo = modelo;
        this.ano = ano;
        this.idCliente = idCliente;
    }

    public Veiculo(Long id, String placa, String modelo, Integer ano, Long idCliente) {
        this.id = id;
        this.placa = placa;
        this.modelo = modelo;
        this.ano = ano;
        this.idCliente = idCliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }
}