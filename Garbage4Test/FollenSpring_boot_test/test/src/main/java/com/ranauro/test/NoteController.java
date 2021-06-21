package com.ranauro.test;

import org.springframework.web.bind.annotation.*;



@RestController
public class NoteController {
	
	private final NoteRepository nr;
	NoteController(NoteRepository repository){
		this.nr = repository;
	}
	
	/**
	 * recupera tutte le note presenti nel db
	 * controlla le noti presenti @ http://127.0.0.1:8080/notes
	 * */
	@GetMapping("/notes")
	Iterable<Note> getNotes(){
		return nr.findAll();
	}
	
	/**
	 * in questo caso voglio cercare la nota attraverso l'id,
	 * lo faccio attraverso il parametro {id}
	 * 
	 * i nometri dei parametri specificati devono essere uguali alle variabili nel codice*/
	@GetMapping("/notes/{id}")
	Note getNote(@PathVariable Long id){
		return nr.findById(id).orElseThrow();
	}
	
	/**
	 * per creare una note dobbiamo effettuare una POST nei servizi rest sulla collection
	 * 
	 * @RequestBody dice che il valore viene preso dal body della richiesta
	 * 
	 * se nel Body mandiamo il valore della nuova nota, questa viene salvata nel DB*/
	@PostMapping("/notes")
	Note createNote(@RequestBody Note newNote) {
		return nr.save(newNote);	//salva e ritorna la nuova nota inserita
	}
	
	
	
}
