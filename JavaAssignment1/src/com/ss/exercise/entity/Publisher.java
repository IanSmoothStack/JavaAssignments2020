/**
 * 
 */
package com.ss.exercise.entity;

import java.util.List;

/**
 * @author Ian
 *
 */
public class Publisher {
	private Integer publisherId;
	private String publisherName;
	private String publisherAddress;
	private List<Book> books;
	private List<Author> authors;
	
	public Publisher(Integer publisherId, String publisherName, String publisherAddress) {
		this.publisherId = publisherId;
		this.publisherName = publisherName;
		this.publisherAddress = publisherAddress;
	}
	
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
	public List<Author> getAuthors() {
		return authors;
	}
	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
	
}
