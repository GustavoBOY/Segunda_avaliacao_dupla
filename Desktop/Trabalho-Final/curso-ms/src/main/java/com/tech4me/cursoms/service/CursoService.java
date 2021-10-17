package com.tech4me.cursoms.service;

import java.util.List;
import java.util.Optional;

import com.tech4me.cursoms.compartilhado.CursoDto;

public interface CursoService {
    
    CursoDto criarCurso(CursoDto curso);
    List<CursoDto> obterTodos();
    Optional<CursoDto> obterPorId(String id);
    List<CursoDto> obterPorEstudante(String estudante);
    void removerCurso(String id);
    boolean definirComoInativo(String id);
    CursoDto atualizarCurso(String id, CursoDto curso);
}
