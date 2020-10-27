/**
 * 
 */
package com.ss.exercise.entity;

/**
 * @author Ian
 *
 */
public class Genre {
	
	private Integer genreId;
	private String genreName;
	
	public Genre(Integer genreId, String genreName) {
		super();
		this.genreId= genreId;
		this.genreName = genreName;
	}
	
	public Integer getGenreId() {
		return genreId;
	}
	public String getGenreName() {
		return genreName;
	}
	
	public void setGenreId(Integer genreId) {
		this.genreId = genreId;
	}
	
	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}
	
	

}
