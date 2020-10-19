/**
 * 
 */
package com.ss.exercise.entity;

import java.util.List;

/**
 * @author Ian
 *
 */
public class NumberOfCopies {
	private Integer numOfCopies;
	private Integer bookId;
	private Integer branchId;
	private Book book;
	private Branch branch;
	
	
	public NumberOfCopies(Integer numOfCopies) {
		this.numOfCopies = numOfCopies;
	}
	
	public int getNumOfCopies() {
		return numOfCopies;
	}
	public void setNumOfCopies(int noc) {
		this.numOfCopies = noc;
	}
	public int getBookId() {
		return book.getBookId();
	}
	public void setBookId(int bookId) {
		this.book.setBookId(bookId);
	}
	public int getBranchId() {
		return branch.getBranchId();
	}
	public void setBranchId(int branchId) {
		this.branch.setBranchId(branchId);
	}
	
	
}
