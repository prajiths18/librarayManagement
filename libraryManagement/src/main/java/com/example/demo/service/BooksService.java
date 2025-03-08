package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Books;
import com.example.demo.repo.BooksRepo;

@Service
public class BooksService {
	@Autowired
    BooksRepo booksRepo;
	
  public List<Books> getBooksList()
  {
	 return booksRepo.findAll();
  }
  public void registerBooks(Books b)
  {
	 // System.out.println("hii");
	  booksRepo.save(b);
  }
    
  public void updateBooks(String name,String author,String bookholder)
  {
	  booksRepo.updateBooksDetails(name,author,bookholder);
  }
  
  public void removeBooks(String name)
  {
	//  System.out.println(name); 
	  booksRepo.removeBooksDetails(name);
  }
  
  public long getBookName(String name)
  {
	  return booksRepo.getCount(name);
  }
  public void updateUserForBooks(String bookName,String name)
  {
	 // System.out.println("insidde");
	  booksRepo.updateUser(bookName,name);
  }
  
  public List<Books> getBooksByUserName(String name)
  {
	 return  booksRepo.getBooksByUser(name);
  }
}
