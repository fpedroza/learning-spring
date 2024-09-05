package com.fmpdmb.learningspring.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends CrudRepository<Guest, Long> {
    
    List<Guest> findByLastNameContaining(String lastName);

}
