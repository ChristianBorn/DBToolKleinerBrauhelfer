package com.sqlite.demo.repository;


import com.sqlite.demo.model.Hefe;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HefeRepository extends CrudRepository<Hefe, Long> {
    @Query("SELECT SUM(menge*preis) FROM Hefe WHERE menge>0")
    Float findPricesOfStockedItems();
}
