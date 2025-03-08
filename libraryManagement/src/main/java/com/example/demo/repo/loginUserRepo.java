package com.example.demo.repo;

import java.util.Optional;

//import java.util.Optional;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import com.example.demo.model.User;
//
//import jakarta.transaction.Transactional;
//
//public interface loginUserRepo extends JpaRepository<User, String>{
//    
//	Optional<User> findByUsername(String username);
//	
//    
//	//@Modifying
//	//@Transactional
//	//@Query("UPDATE User u SET u.password = :password, u.role = :role WHERE u.username = :username")
//	void updateUserDetails(@Param("username") String username, 
//	                      @Param("password") String password, 
//	                      @Param("role") String role); 
//}
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.User;

import org.springframework.data.repository.query.Param;

@Repository
public interface loginUserRepo extends JpaRepository<User, String> {

    Optional<User> findByUsername(String username); 

    @Modifying
//    @Transactional
    @Query("update User u set  u.Role = 'User' where u.username = :username")
    int updateUserDetails(@Param("username") String username, 
                          @Param("password") String password, 
                          @Param("role") String Role);
    @Modifying
    @Transactional
    @Query("delete from User u where u.username= :username ")
    int deleteUserDetails(@Param("username") String username);
}
