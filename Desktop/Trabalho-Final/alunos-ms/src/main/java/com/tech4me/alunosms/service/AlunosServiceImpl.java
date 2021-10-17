package com.tech4me.alunosms.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.tech4me.alunosms.compartilhado.AlunosDto;
import com.tech4me.alunosms.compartilhado.CursoDto;
import com.tech4me.alunosms.http.CursosFeignClient;
import com.tech4me.alunosms.model.Alunos;
import com.tech4me.alunosms.repository.AlunosRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlunosServiceImpl implements AlunosService{
    
    @Autowired
    private AlunosRepository repoAlunos;
    @Autowired
    private CursosFeignClient cursosFeignClient;

    @Override
    public AlunosDto criarAluno(AlunosDto aluno) {
        return salvarAluno(aluno);
    }
    @Override
    public List<AlunosDto> obterTodos() {
        List<Alunos> alunos = repoAlunos.findAll();

        return alunos.stream()
            .map(aluno -> new ModelMapper().map(aluno, AlunosDto.class))
            .collect(Collectors.toList());
    }

    @Override
    public Optional<AlunosDto> obterPorId(String id) {
       Optional<Alunos> aluno = repoAlunos.findById(id);

       if(aluno.isPresent()) {

           AlunosDto dto = new ModelMapper().map(aluno.get(), AlunosDto.class);

           List<CursoDto> cursos = cursosFeignClient.obterPorEstudante(dto.getId());

           dto.setCursos(cursos);
           return Optional.of(dto);
       }

       return Optional.empty();
    }

    @Override
    public void removerAluno(String id) {
        repoAlunos.deleteById(id);
    }

    @Override
    public AlunosDto atualizarAluno(String id, AlunosDto alunos) {
        alunos.setId(id);
        return salvarAluno(alunos);
    }

    private AlunosDto salvarAluno(AlunosDto aluno) {
        ModelMapper mapper = new ModelMapper();
        Alunos alunosEntidade = mapper.map(aluno, Alunos.class);
        alunosEntidade = repoAlunos.save(alunosEntidade);

        return mapper.map(alunosEntidade, AlunosDto.class);
    }
}
