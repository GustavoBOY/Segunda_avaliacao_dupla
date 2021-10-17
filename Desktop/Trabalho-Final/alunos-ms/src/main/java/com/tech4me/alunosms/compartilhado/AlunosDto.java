package com.tech4me.alunosms.compartilhado;

import java.util.List;

public class AlunosDto {

    private String id;
    private String nome;
    private String sobrenome;
    private Integer idade;
    private List<CursoDto> cursos;


    public List<CursoDto> getCursos() {
        return cursos;
    }
    public void setCursos(List<CursoDto> cursos) {
        this.cursos = cursos;
    }
    
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

    
}

