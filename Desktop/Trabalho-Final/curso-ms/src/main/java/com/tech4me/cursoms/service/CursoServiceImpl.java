package com.tech4me.cursoms.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.tech4me.cursoms.compartilhado.CursoDto;
import com.tech4me.cursoms.model.Curso;
import com.tech4me.cursoms.repository.CursoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

@Service
public class CursoServiceImpl implements CursoService{

    @Autowired
    private CursoRepository repositoryCurso;

    @Override
    public CursoDto criarCurso(CursoDto curso) {
        return salvarCurso(curso);
    }

    @Override
    public List<CursoDto> obterTodos() {
        List<Curso> cursos = repositoryCurso.findAll();

        return cursos.stream().map(curso -> new ModelMapper().map(curso, CursoDto.class)).collect(Collectors.toList());
    }
    @Override
    public Optional<CursoDto> obterPorId(String id) {
        Optional<Curso> curso = repositoryCurso.findById(id);

       if(curso.isPresent()) {
           return Optional.of(new ModelMapper().map(curso.get(), CursoDto.class));
       }

       return Optional.empty();
    }
    @Override
    public List<CursoDto> obterPorEstudante(String estudante) {
        List<Curso> cursos = repositoryCurso.findByEstudante(estudante);

        return cursos.stream()
            .map(curso -> new ModelMapper().map(curso, CursoDto.class))
            .collect(Collectors.toList());
    }

    @Override
    public void removerCurso(String id) {
        repositoryCurso.deleteById(id);
    }

    @Override
    public boolean definirComoInativo(String id) {
        Optional<Curso> curso = repositoryCurso.findById(id);
        if(curso.isPresent()) {
            curso.get().setAtivoNoCurso(false);
            repositoryCurso.save(curso.get());

            return true;
        }

        return false;
    }
    @Override
    public CursoDto atualizarCurso(String id, CursoDto curso) {
        curso.setId(id);
        return salvarCurso(curso);
    }

    private CursoDto salvarCurso(CursoDto curso) {
        ModelMapper mapper = new ModelMapper();
        Curso cursoEntidade = mapper.map(curso, Curso.class);
        cursoEntidade = repositoryCurso.save(cursoEntidade);

        return mapper.map(cursoEntidade, CursoDto.class);
    }
}
