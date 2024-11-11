package com.sqlite.demo.repository.aggregation;

import com.sqlite.demo.model.aggregation.YearlyProduction;
import com.sqlite.demo.model.sud.Sud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AggregationRepository extends JpaRepository<Sud, Long> {

    @Query(value = "SELECT SUM(Sud.menge) as production, strftime('%Y', Sud.braudatum) as year " +
            "FROM Sud " +
            "WHERE Sud.braudatum IS NOT NULL " +
            "GROUP BY year", nativeQuery = true)
    List<YearlyProduction> getYearlyProduction();

}
