package com.sqlite.demo.repository;


import com.sqlite.demo.model.Ausruestung;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AusruestungRepository extends CrudRepository<Ausruestung, Long> {
}
