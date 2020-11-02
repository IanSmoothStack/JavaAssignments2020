package com.ss.lms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ss.lms.entity.BookCopies;

@Repository
public interface BookCopiesRepo  extends JpaRepository<BookCopies, Integer> {
	
	@Query(" FROM BookCopies where branchName =:branchName")
	public List<BookCopies> readBookCopiesByBranchName(@Param("branchName") String branchName);
	
	@Query(" FROM BookCopies where title =:title")
	public List<BookCopies> readBookCopiesByBookTitle(@Param("title") String title);
	
	@Query(" FROM BookCopies where bookId =:bookId")
	public List<BookCopies> readBookCopiesByBookId(@Param("bookId") int bookId);
	
	@Query(" FROM BookCopies where branchId =:branchId")
	public List<BookCopies> readBookCopiesByBranchId(@Param("branchId") int branchId);
	
	@Query(" FROM BookCopies where branchId =:branchId and bookId =:bookId")
	public List<BookCopies> readBookCopiesById( @Param("bookId") int bookId, @Param("branchId") int branchId);
	


}
