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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name = "tbl_book")
public class Book implements Serializable {

	private static final long serialVersionUID = 1338085671779511268L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bookId")
	private Integer bookId;

	@Column(name = "title")
	@NonNull
	private String title;
	
	@ManyToMany
	@JoinTable(name = "tbl_book_authors", joinColumns = {@JoinColumn(name="bookId") }, 
		inverseJoinColumns = {@JoinColumn(name="authorId") } )
	private List<Author> authors;
	
	@ManyToMany
	@JoinTable(name = "tbl_book_genres", joinColumns = {@JoinColumn(name="bookId") }, 
	inverseJoinColumns = {@JoinColumn(name="genre_id") } )
	private List<Genre> genres;
	
	
	@ManyToOne
	@JoinColumn(name = "pubId")
	private Publisher publisher;
	
	
	@ManyToMany(fetch = FetchType.LAZY,mappedBy = "books")
	@JsonBackReference
	private List<Branch> branches;
	
	
	
//	@EmbeddedId
//	private List<BookCopies> bookCopies;
	
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
//	@JsonBackReference
//	private List<BookCopies> bookCopies;
	
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bookId")
//	@JsonBackReference
//	private List<BookLoans> bookLoans;
	





	public Integer getBookId() {
		return bookId;
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
	
	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}
	
	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public int getPubId() {
		return publisher.getPublisherId();
	}

	public void setPubId(int pubId) {
		this.publisher.setPublisherId(pubId);
	}
	
	
}