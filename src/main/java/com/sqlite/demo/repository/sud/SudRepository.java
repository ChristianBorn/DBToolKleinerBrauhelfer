package com.sqlite.demo.repository.sud;

import com.sqlite.demo.model.sud.Sud;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SudRepository extends CrudRepository<Sud, Long> {
}
