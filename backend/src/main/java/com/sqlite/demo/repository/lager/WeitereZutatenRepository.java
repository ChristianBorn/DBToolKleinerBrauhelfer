package com.sqlite.demo.repository.lager;

import com.sqlite.demo.model.lager.WeitereZutaten;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeitereZutatenRepository extends CrudRepository<WeitereZutaten, Long> {

}
