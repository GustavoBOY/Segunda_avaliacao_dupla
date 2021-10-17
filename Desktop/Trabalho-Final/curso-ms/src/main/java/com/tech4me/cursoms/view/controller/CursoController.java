package com.tech4me.cursoms.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech4me.cursoms.compartilhado.CursoDto;
import com.tech4me.cursoms.service.CursoService;
import com.tech4me.cursoms.view.model.CursoModeloAlteracao;
import com.tech4me.cursoms.view.model.CursoModeloInclusao;
import com.tech4me.cursoms.view.model.CursoModeloResponse;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {
    @Autowired
    private CursoService service;

    @GetMapping(value="/status")
    public String statusServico(@Value("${local.server.port}") String porta) {
        return String.format("Serviço ativo e executando na porta %s", porta);
    }    

    @PostMapping
    public ResponseEntity<CursoModeloResponse> criarCurso(@RequestBody @Valid CursoModeloInclusao Curso) {
        ModelMapper mapper = new ModelMapper();
        CursoDto dto = mapper.map(Curso, CursoDto.class);
        dto = service.criarCurso(dto);
        return new ResponseEntity<>(mapper.map(dto, CursoModeloResponse.class), HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<CursoModeloResponse>> obterTodos() {
        List<CursoDto> dtos = service.obterTodos();

        if(dtos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        ModelMapper mapper = new ModelMapper();
        List<CursoModeloResponse> resp = dtos.stream()
                    .map(dto -> mapper.map(dto, CursoModeloResponse.class))
                    .collect(Collectors.toList());

        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @GetMapping(value="/{estudante}/lista")
    public ResponseEntity<List<CursoModeloResponse>> obterPorEstudante(@PathVariable String estudante) {
        List<CursoDto> dtos = service.obterPorEstudante(estudante);

        if(dtos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        ModelMapper mapper = new ModelMapper();
        List<CursoModeloResponse> resp = dtos.stream()
                    .map(dto -> mapper.map(dto, CursoModeloResponse.class))
                    .collect(Collectors.toList());

        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
    
    @GetMapping(value="/{id}")
    public ResponseEntity<CursoModeloResponse> obterPorId(@PathVariable String id) {
        Optional<CursoDto> curso = service.obterPorId(id);

        if(curso.isPresent()) {
            return new ResponseEntity<>(
                new ModelMapper().map(curso.get(), CursoModeloResponse.class), 
                HttpStatus.OK
            );
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<CursoModeloResponse> atualizarCurso(@PathVariable String id,
        @Valid @RequestBody CursoModeloAlteracao curso) {
        ModelMapper mapper = new ModelMapper();
        CursoDto dto = mapper.map(curso, CursoDto.class);
        dto = service.atualizarCurso(id, dto);

        return new ResponseEntity<>(mapper.map(dto, CursoModeloResponse.class), HttpStatus.OK);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> removerCurso(@PathVariable String id) {
        service.removerCurso(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(value="/{id}")
    public ResponseEntity<Void> definirComoInativo(@PathVariable String id) {
        if(service.definirComoInativo(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
