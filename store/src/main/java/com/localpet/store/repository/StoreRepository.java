package com.localpet.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.localpet.store.model.Pet;

@Repository
public interface StoreRepository extends JpaRepository<Pet, String>{

	
	
	
	
}
