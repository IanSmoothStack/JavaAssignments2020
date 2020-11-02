package com.ss.lms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.ss.lms.entity.Book;

@Repository
public interface BookRepo extends JpaRepository<Book, Integer>{
	
	@Query(" FROM Book where title =:title")
	public List<Book> readBooksByTitle(@Param("title") String title);
	
	@Query(" FROM Book where bookId =:bookId")
	public List<Book> readBooksById(@Param("bookId") int bookId);
	
	
	@Modifying
	@Query("delete Book where bookId =:bookId")
	int deleteBook(@Param("bookId") int book_id);
	
	@Modifying
	@Query("update Book set title=:title, pubId=:pubId where bookId =:bookId")
	int updateBook(@Param("bookId") int bookId, @Param("title") String title, @Param("pubId") int pubId);
	
//	@Modifying
//	@Query("update Book set title=:title, pubId=:pubId where bookId =:bookId")
//	int updateBookAll(@Param("book") Book book, @Param("title") String title, @Param("pubId") int pubId);
	
//	@Modifying
//	@Query("delete tbl_book_authors where book_id =:book_id")
//	int deleteBookAuthors(@Param("book_id") int book_id);
//	
//	@Modifying
//	@Query("update Book set title=:title, pubId=:pubId where bookId =:bookId")
//	int updateBookAuthors(@Param("bookId") int bookId, @Param("title") String title, @Param("pubId") int pubId);
}