package com.tech4me.alunosms.view.controller;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech4me.alunosms.compartilhado.AlunosDto;
import com.tech4me.alunosms.model.Alunos;
import com.tech4me.alunosms.service.AlunosService;
import com.tech4me.alunosms.view.model.AlunosModeloDetalheResponse;
import com.tech4me.alunosms.view.model.AlunosModeloRequest;
import com.tech4me.alunosms.view.model.AlunosModeloResponse;


@RestController
@RequestMapping("/api/alunos")
public class AlunosController {
    @Autowired
    private AlunosService service;

    @GetMapping(value="/status")
    public String statusServico(@Value("${local.server.port}") String porta) {
        return String.format("Serviço ativo e executando na porta %s", porta);
    }
    

    @PostMapping
    public ResponseEntity<AlunosModeloResponse> criarAluno(@RequestBody @Valid AlunosModeloRequest alunos) {
        ModelMapper mapper = new ModelMapper();
        AlunosDto dto = mapper.map(alunos, AlunosDto.class);
        dto = service.criarAluno(dto);
        return new ResponseEntity<>(mapper.map(dto, AlunosModeloResponse.class), HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<AlunosModeloResponse>> obterTodos() {
        List<AlunosDto> dtos = service.obterTodos();

        if(dtos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        ModelMapper mapper = new ModelMapper();
        List<AlunosModeloResponse> resp = dtos.stream()
                    .map(dto -> mapper.map(dto, AlunosModeloResponse.class))
                    .collect(Collectors.toList());

        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
    
    @GetMapping(value="/{id}")
    public ResponseEntity<AlunosModeloDetalheResponse> obterPorId(@PathVariable String id) {
        Optional<AlunosDto> aluno = service.obterPorId(id);

        if(aluno.isPresent()) {
            return new ResponseEntity<>(
                new ModelMapper().map(aluno.get(), AlunosModeloDetalheResponse.class), 
                HttpStatus.OK
            );
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<AlunosModeloResponse> atualizarAluno(@PathVariable String id,
        @Valid @RequestBody Alunos alunos) {
        ModelMapper mapper = new ModelMapper();
        AlunosDto dto = mapper.map(alunos, AlunosDto.class);
        dto = service.atualizarAluno(id, dto);

        return new ResponseEntity<>(mapper.map(dto, AlunosModeloResponse.class), HttpStatus.OK);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> removerAluno(@PathVariable String id) {
        service.removerAluno(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } 
}
