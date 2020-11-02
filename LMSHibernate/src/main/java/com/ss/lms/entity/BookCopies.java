/**
 * 
 */
package com.ss.lms.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Ian
 *
 */

@Entity
@Table(name = "tbl_book_copies")
public class BookCopies implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8284697113541696454L;

	@Column(name = "noOfCopies")
	private Integer noOfCopies;
	

	
	public BookCopies() {
		super();
	}
	
	
	public BookCopies(BookCopiesId id) {
		super();
		this.id = id;
	}
	
	@EmbeddedId
	private BookCopiesId id;
	
	
	
	

	
	
	
//	public NumberOfCopies(Integer numOfCopies) {
//		this.numOfCopies = numOfCopies;
//	}
	
	public int getNumOfCopies() {
		return noOfCopies;
	}
	public void setNumOfCopies(int noc) {
		this.noOfCopies = noc;
	}


	
	
	
	public BookCopiesId getId() {
		return id;
	}


	public void setId(BookCopiesId id) {
		this.id = id;
	}


//	public int getBookId() {
//		return id.getBookId();
//	}
//	public void setBookId(int bookId) {
//		this.id.setBookId(bookId);
//	}
//	public int getBranchId() {
//		return id.getBranchId();
//	}
//	public void setBranchId(int branchId) {
//		this.id.setBranchId(branchId);
//	}
	
	
	
	
	
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        book.getBookId() that = (book.getBookId()) o;
//        return book.getBookId().equals(that.book.getBookId()) &&
//                departmentId.equals(that.departmentId);
//    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(book.getBookId(), branch.getBranchId());
//    }
	
	
}
