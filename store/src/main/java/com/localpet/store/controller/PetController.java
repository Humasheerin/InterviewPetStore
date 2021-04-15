package com.localpet.store.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.localpet.store.exception.ResourceNotFoundException;
import com.localpet.store.model.Pet;
import com.localpet.store.repository.StoreRepository;

@RestController
@RequestMapping("/api/pets")
public class PetController {

	@Autowired
	private StoreRepository storerepository;
	
	@PostMapping	
	public Pet createPet(@RequestBody Pet pet) {
		
		return storerepository.save(pet);
	}

@PutMapping("/{name}")
	public Pet updatePet(@RequestBody Pet pet,@PathVariable(value="name")String Name) {
		Optional<Pet> petdb= this.storerepository.findById(pet.getName());
	if(petdb.isPresent())
	{
		Pet petupdate= petdb.get();
		petupdate.setName(pet.getName());
		petupdate.setPrice(pet.getPrice());
		petupdate.setType(pet.getType());
		storerepository.save(petupdate);
		
	
	return petupdate;
	}
	else {
		return null;
	}

		
		
	
	}

	@GetMapping
	public List<Pet> getAllPets() {
		

		return storerepository.findAll();
	}

	@GetMapping("/{name}")
	public Pet getPetByName(@PathVariable(value="name")String Name) {
		
		return  this.storerepository.findById(Name).
				orElseThrow(() -> new ResourceNotFoundException( "Pet not found with name"+Name));
		
		
	}

	@DeleteMapping("/{name}")
	public ResponseEntity<Pet> deletePet(@PathVariable(value="name")String Name) {
	
		Pet petdb= this.storerepository.findById(Name).
				orElseThrow(()->new ResourceNotFoundException( "Pet not found with name"+Name));
		this.storerepository.delete(petdb);
	
		return ResponseEntity.ok().build();
	}
	
}
