package com.example.demo.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Books;
import com.example.demo.service.BooksService;

@Controller 
public class UserBookDetails {

    @Autowired
    BooksService booksService;

    // GET: Load user book details
    @GetMapping("user/userbooksdetails")
    public String getUserBookDetails(Principal principal,Model m) {
        List<Books> books = booksService.getBooksList();
        m.addAttribute("books", books);
        
        String username = principal.getName();  // Get logged-in user
        List<Books> myBook = booksService.getBooksByUserName(username); // Fetch books
        //System.out.println(myBook.size());
        m.addAttribute("myBooks", myBook); 
        
        return "userbooksdetails";
    } 

    // POST: Take book request
    @PostMapping("user/userbooksdetails")
    public String takeBook(Principal principal, @RequestParam("bookName") String bookName) {
        long count = booksService.getBookName(principal.getName());
        System.out.println("Count is: " + count);

        if (count < 2) {
            booksService.updateUserForBooks(bookName, principal.getName());
            return "redirect:/user/userbooksdetails"; // Refresh page after book taken
        } else {
            System.out.println("hi - Redirecting to restriction page");
            return "redirect:/user/restrictBooks"; // Redirect to restriction page
        }
    } 
 
    // GET: Restriction page
    @GetMapping("user/restrictBooks")
    public String restrictBooks() {
        return "restrictBooks";
    }


}
