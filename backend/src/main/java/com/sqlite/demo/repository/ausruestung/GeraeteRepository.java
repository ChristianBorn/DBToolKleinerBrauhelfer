package com.sqlite.demo.repository.ausruestung;


import com.sqlite.demo.model.ausruestung.Geraet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeraeteRepository extends CrudRepository<Geraet, Long> {
}
