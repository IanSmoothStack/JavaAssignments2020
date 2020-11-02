package com.ss.lms.repo;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ss.lms.entity.Branch;

@Repository
public interface BranchRepo  extends JpaRepository<Branch, Integer> {
	
	@Query(" FROM Branch where branchName =:branchName")
	public List<Branch> readBranchesByName(@Param("branchName") String branchName);
	
	@Query(" FROM Branch where branchId =:branchId")
	public List<Branch> readBranchesById(@Param("branchId") int branchId);
	
	
	@Modifying
	@Query("delete Branch where branchId =:branchId")
	int deleteBranch(@Param("branchId") int branchId);

}
