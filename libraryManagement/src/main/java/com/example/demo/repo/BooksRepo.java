package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Books;

import jakarta.transaction.Transactional;

@Repository
public interface BooksRepo extends JpaRepository<Books, String>{

	@Modifying
	@Transactional
@Query("update Books u set  u.author = : author , u.bookholder =: bookholder where u.bookName = :bookName")
int updateBooksDetails(@Param("bookName") String bookName, 
                      @Param("author") String author, 
                      @Param("available") String bookholder);

@Modifying
@Transactional
@Query("delete from Books u where u.bookName = :bookName")
int removeBooksDetails(@Param("bookName") String bookName);


@Query("SELECT COUNT(b) FROM Books b WHERE b.bookholder = :bookholder")
long getCount(@Param("bookholder") String bookholder);

@Modifying
@Transactional
@Query("update Books b set b.bookholder = :bookholder where b.bookName = :bookName")
int updateUser(@Param("bookName") String bookName,@Param("bookholder") String bookholder);

@Query("SELECT b FROM Books b WHERE b.bookholder = :bookholder")
List<Books> getBooksByUser(@Param("bookholder") String bookholder);


}
