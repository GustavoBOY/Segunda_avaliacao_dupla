package com.tech4me.alunosms.repository;

import com.tech4me.alunosms.model.Alunos;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AlunosRepository extends MongoRepository<Alunos, String>{
    
}
