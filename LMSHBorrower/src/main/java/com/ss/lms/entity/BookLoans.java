package com.ss.lms.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

@Entity
@Table(name = "tbl_book_loans")
public class BookLoans implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8739877259058820879L;


	
	
	@EmbeddedId
	private BookLoansId id;
	
	@Column(name = "dateOut")
	@NonNull
	private Date dateOut;
	@Column(name = "dueDate")
	private Date dueDate;
	@Column(name = "dateIn")
	private Date dateIn;
	


	
	public BookLoansId getId() {
		return id;
	}
	
	public void setId(BookLoansId id) {
		this.id = id;
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
	



}
