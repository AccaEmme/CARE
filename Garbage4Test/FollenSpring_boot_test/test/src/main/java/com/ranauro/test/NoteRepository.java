package com.ranauro.test;

import org.springframework.data.repository.CrudRepository;

public interface NoteRepository extends CrudRepository<Note,Long>{
	//interfaccia vuota, ci pensa hibernate
}
