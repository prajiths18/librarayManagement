package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Books {

	@Id
	String bookName;
	public Books(String bookName, String author, String bookholder) {
		super();
		this.bookName = bookName;
		this.author = author;
		this.bookholder=bookholder;
	}
	public String getBookholder() {
		return bookholder;
	}
	public void setBookholder(String bookholder) {
		this.bookholder = bookholder;
	}
	String author;
	String bookholder;
	public Books() {};
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	}
	
