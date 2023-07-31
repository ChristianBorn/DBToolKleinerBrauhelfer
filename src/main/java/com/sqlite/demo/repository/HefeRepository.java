package com.sqlite.demo.repository;


import com.sqlite.demo.model.Hefe;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HefeRepository extends CrudRepository<Hefe, Long> {
    @Query("SELECT menge * preis FROM Hefe WHERE menge>0")
    List<Float> findPricesOfStockedItems();
}
