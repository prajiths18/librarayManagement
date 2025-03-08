package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
@Controller 
public class LoginController { 

   @GetMapping("/login") 
   public String login() { 
      return "login"; 
   }
   
   @GetMapping("/user")
   public String userHome(Model model) {
       model.addAttribute("role", "User");
       return "welcome"; // Returns user-home.html
   }
   
   @GetMapping("/admin")
   public String adminHome(Model model, Principal principal ,Authentication authentication) {
	   if (!authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_Admin"))) {
           return "AccessDenied"; // 🚫 Redirect unauthorized users
       }
       if (principal != null) {
    	//   System.out.println(principal.getName());
           model.addAttribute("username", principal.getName());
       }
       return "admin"; 
   }

}