package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repo.loginUserRepo;

@Service
public class registerService {
   
	@Autowired
	loginUserRepo loginuserRepo;
	
      
	
	public void getUserDetails(User u)
	{
		   
	     //	System.out.print("dfv");
	     	loginuserRepo.save(u);
	}
	public void updateUserDetails(User u)
	{
		loginuserRepo.updateUserDetails(u.getUsername(),u.getPassword(),u.getRole());
	}
	public void deleteUser(String username)
	{
		loginuserRepo.deleteUserDetails(username);	
	}
}
