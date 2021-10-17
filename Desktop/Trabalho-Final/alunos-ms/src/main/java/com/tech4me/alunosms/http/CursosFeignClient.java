package com.tech4me.alunosms.http;

import java.util.ArrayList;
import java.util.List;

import com.tech4me.alunosms.compartilhado.CursoDto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "curso-ms", fallback = CursoFeignClientFallback.class)
public interface CursosFeignClient {

    @GetMapping(path = "/api/cursos/{estudante}/lista")
    List<CursoDto> obterPorEstudante(@PathVariable String idEstudante);
}
@Component
class CursoFeignClientFallback implements CursosFeignClient{
    
    @Override
    public List<CursoDto> obterPorEstudante(String estudante){
     return new ArrayList<>();
    }
}
