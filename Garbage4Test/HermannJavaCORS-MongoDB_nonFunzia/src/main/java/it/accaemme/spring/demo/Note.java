package it.accaemme.spring.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//import lombok.Data;


//@Data
@Entity
public class Note{
	 private @Id @GeneratedValue Long id;
	 private String title;
	 private String content;

	 Note(){}

	 public Note(String title, String content){
	  super();
	  this.title = title;
	  this.content = content;
	 }

	public Long getId() {
		return id;
	}

	private void setId(Long id) {
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