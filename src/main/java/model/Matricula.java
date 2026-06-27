package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Matricula {

    private Long       id;
    private Aluno      aluno;
    private Curso      curso;
    private LocalDate  dataMatricula;
    private BigDecimal valor;

    public Matricula(Long id, Aluno aluno, Curso curso, LocalDate dataMatricula, BigDecimal valor) {
        this.id            = id;
        this.aluno         = aluno;
        this.curso         = curso;
        this.dataMatricula = dataMatricula;
        this.valor         = valor;
    }

    public Matricula(Aluno aluno, Curso curso, LocalDate dataMatricula, BigDecimal valor) {
        this(null, aluno, curso, dataMatricula, valor);
    }

    public Long       getId()            { return id; }
    public Aluno      getAluno()         { return aluno; }
    public Curso      getCurso()         { return curso; }
    public LocalDate  getDataMatricula() { return dataMatricula; }
    public BigDecimal getValor()         { return valor; }

    public void setId(Long id) { this.id = id; }

    @Override
    public String toString() {
        return "Matricula{id=" + id + ", aluno='" + aluno.getNome()
                + "', curso='" + curso.getNome() + "', data=" + dataMatricula + ", valor=R$" + valor + "}";
    }
}
