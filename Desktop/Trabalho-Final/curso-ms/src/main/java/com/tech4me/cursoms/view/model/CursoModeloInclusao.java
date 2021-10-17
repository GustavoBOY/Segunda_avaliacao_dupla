package com.tech4me.cursoms.view.model;
import javax.validation.constraints.NotEmpty;

public class CursoModeloInclusao {
    
    @NotEmpty(message = "O id do estudante deve ser preenchido")
    private String estudante;
    private String nomeDoCurso;
    private Integer duracao;

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


}
