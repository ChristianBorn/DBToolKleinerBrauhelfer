package com.sqlite.demo.repository;


import com.sqlite.demo.model.Geraete;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeraeteRepository extends CrudRepository<Geraete, Long> {
}
