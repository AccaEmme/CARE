 package it.accaemme.spring.demo;

import org.springframework.data.repository.CrudRepository;

public interface NoteRepository extends CrudRepository<Note, Long>{
	
}
