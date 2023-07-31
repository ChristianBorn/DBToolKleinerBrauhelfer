package com.sqlite.demo.repository;


import com.sqlite.demo.model.Hopfen;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HopfenRepository extends CrudRepository<Hopfen, Long> {
    @Query("SELECT menge * preis FROM Hopfen WHERE menge>0")
    List<Float> findPricesOfStockedItems();
}
