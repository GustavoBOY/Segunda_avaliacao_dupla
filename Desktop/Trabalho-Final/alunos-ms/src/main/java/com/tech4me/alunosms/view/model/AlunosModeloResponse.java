package com.tech4me.alunosms.view.model;

public class AlunosModeloResponse {
    private String id;
    private String nome;
    private String sobrenome;
    private Integer idade;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }
    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public Integer getIdade() {
        return idade;
    }
    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getNomeCompleto() {
        return String.format("%s %s", nome, sobrenome);
    }
}
