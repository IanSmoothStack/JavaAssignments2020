package com.ss.lms.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class BookLoansId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4706833646876663069L;

	
	@Column(name = "bookId")
	private Integer bookId;
	
	@Column(name = "branchId")
	private Integer branchId;
	
	@Column(name = "cardNo")
	private Integer cardNo;
	
	public BookLoansId() {
	super();
}
public BookLoansId (Integer bookId, Integer branchId, Integer cardNo) {
	super();
	this.bookId = bookId;
	this.branchId = branchId;
	this.cardNo = cardNo;
}
public Integer getBookId() {
	return bookId;
}
public void setBookId(Integer bookId) {
	this.bookId = bookId;
}
public Integer getBranchId() {
	return branchId;
}
public void setBranchId(Integer branchId) {
	this.branchId = branchId;
}
public Integer getCardNo() {
	return cardNo;
}
public void setCardNo(Integer cardNo) {
	this.cardNo = cardNo;
}




	
}
