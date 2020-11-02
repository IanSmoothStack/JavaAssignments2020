package com.ss.lms.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

//@Entity
@Table(name = "tbl_book_loans")
public class BookLoans {
	
	@ManyToOne
	@JoinColumn(name = "bookId")
	private List<Book> books;
	
	private int bookId;
//	@ManyToOne
//	@JoinColumn(name = "branchId")
	private int branchId;
//	@ManyToOne
//	@JoinColumn(name = "cardNo")
	private int cardNo;
	
	@Column(name = "dateOut")
	@NonNull
	private Date dateOut;
	@Column(name = "dueDate")
	private Date dueDate;
	@Column(name = "dateIn")
	private Date dateIn;
	
//	public Loans(int bookId, int branchId, int cardNo, Date dateOut, Date dueDate, Date dateIn) {
//		this.dateOut = dateOut;
//		this.dueDate = dueDate;
//		this.dateIn = dateIn;
//		this.bookId = bookId;
//		this.branchId = branchId;
//		this.cardNo = cardNo;
//	}

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
