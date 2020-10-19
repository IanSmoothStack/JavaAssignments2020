package com.ss.exercise.entity;

import java.util.Date;

public class Loans {
	
	private int bookId;
	private int branchId;
	private int cardNo;
	

	private Date dateOut;
	private Date dueDate;
	private Date dateIn;
	
	public Loans(int bookId, int branchId, int cardNo, Date dateOut, Date dueDate, Date dateIn) {
		this.dateOut = dateOut;
		this.dueDate = dueDate;
		this.dateIn = dateIn;
		this.bookId = bookId;
		this.branchId = branchId;
		this.cardNo = cardNo;
	}

	public Date getDateOut() {
		return dateOut;
	}

	public void setDateOut(Date dateOut) {
		this.dateOut = dateOut;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getDateIn() {
		return dateIn;
	}

	public void setDateIn(Date dateIn) {
		this.dateIn = dateIn;
	}
	

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public int getCardNo() {
		return cardNo;
	}

	public void setCardNo(int cardNo) {
		this.cardNo = cardNo;
	}

}
