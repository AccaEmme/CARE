package com.ranauro.test;

import javax.persistence.*;


@Entity
public class Note {
	private @Id @GeneratedValue Long id;	//annoto questa variabile in modo tale che jpa la riconosca come chiave primaria. Il valore è generato dal server
	private String title;
	private String content;
	
	Note(){}

	public Note(String title, String content) {
		super();
		this.title = title;
		this.content = content;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}
