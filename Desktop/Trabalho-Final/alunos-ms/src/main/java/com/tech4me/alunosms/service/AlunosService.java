package com.tech4me.alunosms.service;

import java.util.List;
import java.util.Optional;

import com.tech4me.alunosms.compartilhado.AlunosDto;

public interface AlunosService {
    
    AlunosDto criarAluno(AlunosDto aluno);
    List<AlunosDto> obterTodos();
    Optional<AlunosDto> obterPorId(String id);
    void removerAluno(String id);
    AlunosDto atualizarAluno(String id, AlunosDto alunos);
}
