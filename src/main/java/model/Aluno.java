package model;

public class Aluno {

    private Long   id;
    private String nome;
    private String email;
    private String telefone;

    public Aluno(Long id, String nome, String email, String telefone) {
        this.id       = id;
        this.nome     = nome;
        this.email    = email;
        this.telefone = telefone;
    }

    public Aluno(String nome, String email, String telefone) {
        this(null, nome, email, telefone);
    }

    public Long   getId()       { return id; }
    public String getNome()     { return nome; }
    public String getEmail()    { return email; }
    public String getTelefone() { return telefone; }

    public void setId(Long id) { this.id = id; }

    @Override
    public String toString() {
        return "Aluno{id=" + id + ", nome='" + nome + "', email='" + email + "', telefone='" + telefone + "'}";
    }
}
