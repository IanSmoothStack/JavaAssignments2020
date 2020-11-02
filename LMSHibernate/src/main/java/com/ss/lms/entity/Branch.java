/**
 * 
 */
package com.ss.lms.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * @author Ian
 *
 */

@Entity
@Table(name = "tbl_library_branch")
public class Branch implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6744826315266138282L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "branchId")
	private Integer branchId;
	
	@Column(name = "branchName")
	@NonNull
	private String branchName;
	
	@Column(name = "branchAddress")
	private String branchAddress;
	
	@ManyToMany
	@JoinTable(name = "tbl_book_copies", joinColumns = {@JoinColumn(name="bookId") }, 
		inverseJoinColumns = {@JoinColumn(name="branchId") } )
	private List<Book> books;	
	
//	@EmbeddedId
//	private List<BookCopies> bookCopies;
	
	
	
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "branch")
//	@JsonBackReference
//	private List<BookCopies> bookCopies;
	
	
//	@ManyToMany
//	@JoinTable(name = "tbl_book_loans", joinColumns = {@JoinColumn(name="bookId") }, 
//		inverseJoinColumns = {@JoinColumn(name="branchId") } )
//	private List<Loans> loans;	
	
	
//	public Branch(Integer branchId, String branchName, String branchAddress) {
//		super();
//		this.branchId = branchId;
//		this.branchName = branchName;
//		this.branchAddress = branchAddress;
//	}
	
	public Integer getBranchId() {
		return branchId;
	}
	public String getBranchName() {
		return branchName;
	}
	
	public String getBranchAddress() {
		return branchAddress;
	}
	
	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public void setBranchAddress(String branchAddress) {
		this.branchAddress= branchAddress;
	}
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
	

}
