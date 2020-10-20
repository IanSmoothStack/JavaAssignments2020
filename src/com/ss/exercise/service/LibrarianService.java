/**
 * 
 */
package com.ss.exercise.service;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ss.exercise.dao.BranchDAO;
import com.ss.exercise.dao.NumberOfCopiesDAO;
import com.ss.exercise.dao.PublisherDAO;
import com.ss.exercise.entity.Book;
import com.ss.exercise.entity.Branch;
import com.ss.exercise.entity.NumberOfCopies;
import com.ss.exercise.entity.Publisher;

/**
 * @author Ian
 *
 */
public class LibrarianService {
	public ConnectionUtil conUtil = new ConnectionUtil();
	
	
	public void updateBranchName(Branch userBranch) {
		try(Connection conn = conUtil.getConnection()) {
			BranchDAO bdao = new BranchDAO(conn);
				 bdao.updateBranchName(userBranch);
				 System.out.println(" success");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			
		}
	}
	
	public void updateBranchAddress(Branch userBranch) {
		try(Connection conn = conUtil.getConnection()) {
			BranchDAO bdao = new BranchDAO(conn);
				 bdao.updateBranchAddress(userBranch);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			
		}
	}
	
	
	public List<Branch> getBranches(String searchString) {
		try(Connection conn = conUtil.getConnection()) {
			BranchDAO bdao = new BranchDAO(conn);
			if (searchString != null) {
				return bdao.readAllBranchesByName(searchString);
			} else {
				return bdao.readAllBranches();
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<NumberOfCopies> getNumberOfCopies(int bookId, int branchId){
		try(Connection conn = conUtil.getConnection()) {
			NumberOfCopiesDAO ndao = new NumberOfCopiesDAO(conn);
			
				return ndao.getNumOfBookCopies(bookId, branchId);
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public void updateNumOfCopies(int bookId, int branchId, int newNumOfCopies) {
		try(Connection conn = conUtil.getConnection()) {
			NumberOfCopiesDAO ndao = new NumberOfCopiesDAO(conn);
			
				 ndao.updateNumOfCopies(bookId, branchId, newNumOfCopies);
				 System.out.println(" success");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			
		}
	}
		
		public void addCopies(int bookId, int branchId, int newNumOfCopies) {
			try(Connection conn = conUtil.getConnection()) {
				NumberOfCopiesDAO ndao = new NumberOfCopiesDAO(conn);
				
					 ndao.addCopies( bookId, branchId, newNumOfCopies);
					 System.out.println(" success");
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				
			}
	}
		
		
		
		public void deleteBranch(Branch branch) {
			try(Connection conn = conUtil.getConnection()) {
				BranchDAO adao = new BranchDAO(conn);
				NumberOfCopiesDAO ndao = new NumberOfCopiesDAO(conn);
					 ndao.deletedBranch(branch.getBranchId());
					 adao.deleteBranch(branch);
					 conn.commit();
					 System.out.println(" success");
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				
			}
		}
		public void addBranch(Branch branch) {
			try(Connection conn = conUtil.getConnection()) {
				BranchDAO adao = new BranchDAO(conn);
					// adao.addBranch(branch);
					 branch.setBranchId(adao.addBranchWithPk(branch));
					 conn.commit();
					 System.out.println(" success");
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				
			}
		}
		

}
