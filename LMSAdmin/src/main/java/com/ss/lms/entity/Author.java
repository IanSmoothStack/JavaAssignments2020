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
public class Author implements Serializable{
	// an interface without any methods - Marker Interface. 
	//1347369386461096910L
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3076778140388491367L;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authorId == null) ? 0 : authorId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Author other = (Author) obj;
		if (authorId == null) {
			if (other.authorId != null)
				return false;
		} else if (!authorId.equals(other.authorId))
			return false;
		return true;
	}
	/**
	 * 
	 */
	
	private final String str = "Test";
	private Integer authorId;
	private String authorName;
	private List<Book> books;
	
	public Author(Integer authorId, String authorName) {
		this.authorId = authorId;
		this.authorName = authorName;
	}
	
	public Integer getAuthorId() {
		return authorId;
	}
	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
}