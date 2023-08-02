package com.sqlite.demo.repository.ausruestung;


import com.sqlite.demo.model.ausruestung.Ausruestung;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AusruestungRepository extends CrudRepository<Ausruestung, Long> {
}
