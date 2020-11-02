package com.ss.lms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ss.lms.entity.BookLoans;

@Repository
public interface BookLoansRepo  extends JpaRepository<BookLoans, Integer> {
	
	@Query(" FROM BookLoans where branchName =:branchName")
	public List<BookLoans> readBookLoansByName(@Param("branchName") String branchName);
	
	@Query(" FROM BookLoans where bookId =:bookId")
	public List<BookLoans> readBookLoansByBookId(@Param("bookId") int bookId);
	
	@Query(" FROM BookLoans where branchId =:branchId")
	public List<BookLoans> readBookLoansByBranchId(@Param("branchId") int branchId);
	
	@Query(" FROM BookLoans where cardNo =:cardNo")
	public List<BookLoans> readBookLoansByCardNo(@Param("cardNo") int cardNo);
	
	@Query(" FROM BookLoans where branchId =:branchId and bookId =:bookId and cardNo =:cardNo")
	public List<BookLoans> readBookLoansById( @Param("bookId") int bookId, @Param("branchId") int branchId, @Param("cardNo") int cardNo);
	
	


}
