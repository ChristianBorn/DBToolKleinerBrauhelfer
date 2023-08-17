package com.sqlite.demo.repository.gebinde;

import com.sqlite.demo.model.gebinde.Gebinde;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
public interface GebindeRepository extends CrudRepository<Gebinde, Long> {

    @Query("SELECT SUM(fassungsvermoegen*anzahl) FROM Gebinde WHERE status = 'leer'")
    Float getFreeCapacities();
    boolean existsByName(String name);

    @Transactional
    @Modifying
    @Query("UPDATE Gebinde g " +
            "SET g.anzahl = g.anzahl - :numberOfGebindeToFill " +
            "WHERE g.name = :gebindeNameToAlter AND g.status = 'leer'")
    void updateEmptyByName(@Param("gebindeNameToAlter") String gebindeNameToAlter,
                             @Param("numberOfGebindeToFill") int numberOfGebindeToFill);
    @Transactional
    @Modifying
    @Query("UPDATE Gebinde g " +
            "SET g.anzahl = g.anzahl + :numberOfGebindeToFill " +
            "WHERE g.name = :gebindeNameToAlter AND g.status = 'voll'")
    void updateFullByName(@Param("gebindeNameToAlter") String gebindeNameToAlter,
                            @Param("numberOfGebindeToFill") int numberOfGebindeToFill);
}
