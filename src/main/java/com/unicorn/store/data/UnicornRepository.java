package com.unicorn.store.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.unicorn.store.model.Unicorn;

@Repository
public interface UnicornRepository extends CrudRepository<Unicorn, String> {
}
