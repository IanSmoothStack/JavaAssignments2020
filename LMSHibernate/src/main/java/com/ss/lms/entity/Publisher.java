/**
 * 
 */
package com.ss.lms.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * @author Ian
 *
 */
@Entity
@Table(name = "tbl_publisher")
public class Publisher {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "publisherId")
	private Integer publisherId;
	
	@Column(name = "publisherName")
	@NonNull
	private String publisherName;
	
	@Column(name = "publisherAddress")
	private String publisherAddress;
	


	@OneToMany(fetch = FetchType.LAZY, mappedBy = "publisher")
	@JsonBackReference
	private List<Book> books;
//	private List<Author> authors;
	

	
	public Integer getPublisherId() {
		return publisherId;
	}
	public String getPublisherName() {
		return publisherName;
	}
	public String getPublisherAddress() {
		return publisherAddress;
	}
	
	public void setPublisherId(Integer publisherId) {
		this.publisherId = publisherId;
	}
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}
	public void setPublisherAddress(String publisherAddress) {
		this.publisherAddress = publisherAddress;
	}
	
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
//	public List<Author> getAuthors() {
//		return authors;
//	}
//	public void setAuthors(List<Author> authors) {
//		this.authors = authors;
//	}
	
}
