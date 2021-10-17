package com.tech4me.cursoms.repository;

import java.util.List;

import com.tech4me.cursoms.model.Curso;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends MongoRepository<Curso, String> {
    List<Curso> findByEstudante(String estudante);
}
