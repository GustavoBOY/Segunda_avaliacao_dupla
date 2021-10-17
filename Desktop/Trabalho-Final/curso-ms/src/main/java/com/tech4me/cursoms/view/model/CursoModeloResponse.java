package com.tech4me.cursoms.view.model;

public class CursoModeloResponse {
    
    private String id;
    private String estudante;
    private String nomeDoCurso;
    private Integer duracao;
    private Boolean ativoNoCurso;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getEstudante() {
        return estudante;
    }
    public void setEstudante(String estudante) {
        this.estudante = estudante;
    }
    
    public String getNomeDoCurso() {
        return nomeDoCurso;
    }
    public void setNomeDoCurso(String nomeDoCurso) {
        this.nomeDoCurso = nomeDoCurso;
    }

    public Integer getDuracao() {
        return duracao;
    }
    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }

    public Boolean getAtivoNoCurso() {
        return ativoNoCurso;
    }
    public void setAtivoNoCurso(Boolean ativoNoCurso) {
        this.ativoNoCurso = ativoNoCurso;
    }

    
}
