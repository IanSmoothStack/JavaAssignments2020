/**
 * 
 */
package com.ss.exercise.entity;
import java.util.List;

/**
 * @author Ian
 *
 */
public class Book {
	private Integer bookId;
	private String title;
	private List<Author> authors;
	private List<Genre> genres;
	private List<Branch> branches;
	private Publisher publisher;
	private Integer pubId;
	
	public Integer getBookId() {
		return bookId;
	}
	public Book(Integer bookId, String title/*, Integer pubId*/) {
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
	
	public List<Genre> getGenres(){
		return genres;
	}
	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}
	public List<Branch> getBranches(){
		return branches;
	}
	public void setBranches(List<Branch> branches) {
		this.branches = branches;
	}
	public Publisher getPublisher() {
		return publisher;
	}
	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}
	public Integer getPubId() {
		return publisher.getPublisherId();
	}
	public void setPubId(Integer pubId) {
		this.publisher.setPublisherId(pubId);
	}
}