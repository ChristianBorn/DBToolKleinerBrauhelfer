package com.sqlite.demo.repository;

import com.sqlite.demo.model.Gebinde;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GebindeRepository extends CrudRepository<Gebinde, Long> {
}
