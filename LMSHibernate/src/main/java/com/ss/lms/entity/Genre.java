/**
 * 
 */
package com.ss.lms.entity;


import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

/**
 * @author Ian
 *
 */
@Entity
@Table(name = "tbl_genre")
public class Genre implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7923887367449256296L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "genre_id")
	private Integer genreId;
	
	@Column(name = "genre_name")
	@NonNull
	private String genreName;
	
//	public Genre(Integer genreId, String genreName) {
//		super();
//		this.genreId= genreId;
//		this.genreName = genreName;
//	}
	
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
