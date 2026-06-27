package model;

public class Curso {

    private Long   id;
    private String nome;
    private String descricao;
    private int    cargaHoraria;
    private int    vagasTotais;
    private int    vagasDisponiveis;

    public Curso(Long id, String nome, String descricao, int cargaHoraria, int vagasTotais, int vagasDisponiveis) {
        this.id               = id;
        this.nome             = nome;
        this.descricao        = descricao;
        this.cargaHoraria     = cargaHoraria;
        this.vagasTotais      = vagasTotais;
        this.vagasDisponiveis = vagasDisponiveis;
    }

    public Curso(String nome, String descricao, int cargaHoraria, int vagasTotais) {
        this(null, nome, descricao, cargaHoraria, vagasTotais, vagasTotais);
    }

    public Long   getId()               { return id; }
    public String getNome()             { return nome; }
    public String getDescricao()        { return descricao; }
    public int    getCargaHoraria()     { return cargaHoraria; }
    public int    getVagasTotais()      { return vagasTotais; }
    public int    getVagasDisponiveis() { return vagasDisponiveis; }

    public void setId(Long id)                         { this.id = id; }
    public void setVagasDisponiveis(int v)             { this.vagasDisponiveis = v; }

    @Override
    public String toString() {
        return "Curso{id=" + id + ", nome='" + nome + "', cargaHoraria=" + cargaHoraria
                + ", vagas=" + vagasDisponiveis + "/" + vagasTotais + "}";
    }
}
