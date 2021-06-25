package it.accaemme.spring.demo;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoteController {
	private final NoteRepository noteRepository;
	
	public NoteController(NoteRepository repository) {
		noteRepository  = repository;
	}

	@GetMapping("/notes")
	public Iterable<Note> getNotes(){
		return noteRepository.findAll();
	}
	
	@GetMapping("/notes/{noteId}")
	public Note getNotes(@PathVariable long noteId){
		return noteRepository.findById(noteId).orElseThrow();
	}
	
	@PostMapping("/notes")
	public Note createNote(@RequestBody Note newNote) {
		return noteRepository.save(newNote);
	}
	
	@PutMapping("/notes/{noteId}")
	public Note updateNote(@PathVariable Long noteId, @RequestBody Note noteDto) {
		// Dto è un Data Transfer Object ovvero contiene i dati ma non li rappresenta davvero nel nostro database.
		
		Note noteToUpdate = noteRepository.findById(noteId).orElseThrow();
		//noteToUpdate.setID( noteDto.getID() ); gli id non si possono aggiornare per nostra scelta.
		noteToUpdate.setTitle( noteDto.getTitle() );
		noteToUpdate.setContent( noteDto.getContent() );
		return noteRepository.save(noteToUpdate); // salviamo la nota nel database e ritorniamo ciò che ha salvato
	}
	
	@DeleteMapping("/notes/{noteId}")
	void deleteNote(@PathVariable Long noteId) {
		Note note = noteRepository.findById(noteId).orElseThrow();
		noteRepository.delete(note);
	}
	
}
