package com.sqlite.demo.repository;


import com.sqlite.demo.model.lager.Hopfen;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HopfenRepository extends CrudRepository<Hopfen, Long> {
    @Query("SELECT SUM(menge*(preis/1000)) FROM Hopfen WHERE menge>0")
    Float findPricesOfStockedItems();
}
