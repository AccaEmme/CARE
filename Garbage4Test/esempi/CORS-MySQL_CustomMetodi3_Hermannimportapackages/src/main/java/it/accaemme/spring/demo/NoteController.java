package it.accaemme.spring.demo;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin("*")
@RestController
public class NoteController implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        // TODO Auto-generated method stub
        responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
        responseContext.getHeaders().add("Access-Control-Allow-Headers",
                "CSRF-Token, X-Requested-By, Authorization, Content-Type");
        responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
        responseContext.getHeaders().add("Access-Control-Allow-Methods",
                "GET, POST, PUT, DELETE, OPTIONS, HEAD");
    }
   
	private final NoteRepository noteRepository;
	
	public NoteController(NoteRepository repository) {
		noteRepository  = repository;
	}

	@GetMapping("/notes")
	public Iterable<Note> getNotes(){
		return noteRepository.findAll();
	}
	
	/*
	@GetMapping("/notes/{noteId}")
	public Note getNotes(@PathVariable long noteId){
		return noteRepository.findById(noteId).orElseThrow();
	}*/
	@GetMapping("/notes/{title}")
	public Iterable<Note> getNotesbytitle(@PathVariable String title){
		return noteRepository.findByTitle(title);   /*.orElseThrow();*/
	}

	@PostMapping("/note")
	public Note createSingleNote(@RequestBody Note noteJustTitle) {
		//Note n = new Note(noteTitle, "HoVogliaDiThe");
		noteJustTitle.setContent("HoVogliaDiGelato");
		return noteRepository.save(noteJustTitle);
	}
	
	@PostMapping("/note2")
	public Note createSingleNote2(@RequestBody Note noteJustContent) {
		//Note n = new Note(noteTitle, "HoVogliaDiThe");
		noteJustContent.setTitle("HoVogliaDiNutella");
		return noteRepository.save(noteJustContent);
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
