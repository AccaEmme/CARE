 package it.accaemme.spring.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


	@Repository
	public interface NoteRepository extends JpaRepository<Note, Long>{
		
	    @Query("FROM Note n  WHERE n.title =:title")
	    Iterable<Note> findByTitle(@Param("title") String title);
	}


