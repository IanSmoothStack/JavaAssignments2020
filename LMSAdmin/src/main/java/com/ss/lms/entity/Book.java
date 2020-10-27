/**
 * 
 */
package com.ss.lms.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author ppradhan
 *
 */
public class Book implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1338085671779511268L;
	
	private Integer bookId;
	private String title;
	private List<Author> authors;
//	private List<Genre> genres;
//	private List<Branch> branches;
//	private Publisher publisher;
	public Integer getBookId() {
		return bookId;
	}
	public Book(Integer bookId, String title) {
		super();
		this.bookId = bookId;
		this.title = title;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<Author> getAuthors() {
		return authors;
	}
	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
}