package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Books;
import com.example.demo.model.User;
import com.example.demo.service.BooksService;
import com.example.demo.service.registerService;

@Controller
public class adminController { 

	 
	@Autowired 
	PasswordEncoder pass;
	@Autowired
	registerService registerService;
	@Autowired
	BooksService booksservice;
    @GetMapping("/admin/register")
    public String showRegisterPage(Model model) {
        return "register";
    }
    @PostMapping("/admin/register")
    public String getRegisterPage(@RequestParam("username") String username,@RequestParam("password") String password,@RequestParam("role") String role)
    { 
    	User u=new User();
    	u.setUsername(username);
    	u.setPassword(pass.encode(password));
    	u.setRole(role);
    	registerService.getUserDetails(u);
    	return "register";
    }
    
	
    @GetMapping("/admin/updateUser")
    public String showupdateUserPage(Model model) {
        return "register";
    }
    @PostMapping("/admin/updateUser")
    public String getupdateUserPage(@RequestParam("username") String username,@RequestParam("password") String password,@RequestParam("role") String role)
    {
    	User u=new User();
    	u.setUsername(username);
    	u.setPassword(pass.encode(password));
    	u.setRole(role);
    	registerService.getUserDetails(u);
    	return "register";
    }
    @GetMapping("/admin/deleteUser")
    public String showDeleteUserPage(Model model) {
        return "deleteUser";
    }
    @PostMapping("/admin/deleteUser")
    public String deleteUserPage(@RequestParam("username") String username)
    {
    	registerService.deleteUser(username);
    	return "deleteUser";
    } 
    @GetMapping("/admin/bookRegister")
     public String showbookRegister(Model model) {
        return "bookRegister";
    }
    @PostMapping("/admin/bookRegister")
    public String getbookRegister(@RequestParam("bookName") String bookName,@RequestParam("author") String author) {
    	Books b=new Books();
    	b.setBookName(bookName);
    	b.setAuthor(author);
    	b.setBookholder("admin");
    	booksservice.registerBooks(b);
    	//System.out.println("rre");
       return "bookRegister";
   }
     
    @GetMapping("/admin/updateBooks")
    public String showUpdateBooks(Model model) {
       return "bookRegister";
   }
    
    @PostMapping("/admin/updateBooks")
    public String getUpdateBooks(@RequestParam("bookName") String bookName,@RequestParam("author") String author,@RequestParam("available") String bookHolder) {
       
       booksservice.updateBooks(bookName,author,"admin");
       return "bookRegister";
   }
    
    @GetMapping("/admin/deleteBooks")
    public String showDeleteBooks(Model model) {
       return "deleteBooks";
   }
    @PostMapping("/admin/deleteBooks")
    public String getDeleteBooks(@RequestParam("bookName") String bookName) {
       booksservice.removeBooks(bookName);
       return "deleteBooks";
   }
    @GetMapping("/admin/viewBooks")
    public String listBooks(Model m) {
       List<Books> books = booksservice.getBooksList();// getAllBooks();
       m.addAttribute("books", books);
        return "viewBooks";
    }
    
}

