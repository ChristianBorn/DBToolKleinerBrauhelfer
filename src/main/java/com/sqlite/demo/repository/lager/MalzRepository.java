package com.sqlite.demo.repository.lager;

import com.sqlite.demo.model.lager.Malz;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MalzRepository extends CrudRepository<Malz, Long> {
    @Query("SELECT SUM(menge*preis) FROM Malz WHERE menge>0")
    Float findPricesOfStockedItems();
}
