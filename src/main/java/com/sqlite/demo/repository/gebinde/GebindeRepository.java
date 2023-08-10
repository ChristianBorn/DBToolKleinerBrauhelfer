package com.sqlite.demo.repository.gebinde;

import com.sqlite.demo.model.gebinde.Gebinde;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GebindeRepository extends CrudRepository<Gebinde, Long> {
    @Query("SELECT SUM(fassungsvermoegen*anzahl) FROM Gebinde WHERE status = 'leer'")
    Float getFreeCapacities();
}
