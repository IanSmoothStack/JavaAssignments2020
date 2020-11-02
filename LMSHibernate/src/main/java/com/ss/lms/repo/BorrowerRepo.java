package com.ss.lms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



import com.ss.lms.entity.Borrower;

@Repository
public interface BorrowerRepo extends JpaRepository<Borrower, Integer>{
	
	@Query(" FROM Borrower where name =:name")
	public List<Borrower> readBorrowersByName(@Param("name") String name);
	
	@Query(" FROM Borrower where cardNo =:cardNo")
	public List<Borrower> readBorrowersByCardNo(@Param("cardNo") int cardNo);
	
	
	@Modifying
	@Query("delete Borrower where cardNo =:cardNo")
	int deleteBorrower(@Param("cardNo") int cardNo);

}
