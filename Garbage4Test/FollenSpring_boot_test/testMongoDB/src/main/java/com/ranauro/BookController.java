package com.ranauro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {
	@Autowired
	private BookRepository repo;
	BookController(BookRepository book) {
		this.repo = book;
	}
	
	
	@PostMapping("/addBook")	//flaggo come post
	public Book saveBook(@RequestBody Book book) {
		return repo.save(book);
	}
}
