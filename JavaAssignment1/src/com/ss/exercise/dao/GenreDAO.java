/**
 * 
 */
package com.ss.exercise.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.exercise.entity.Author;
import com.ss.exercise.entity.Branch;
import com.ss.exercise.entity.Genre;

/**
 * @author Ian
 *
 */
public class GenreDAO extends BaseDAO<Genre> {

	public GenreDAO(Connection conn) {
		super(conn);
	}

	
	public List<Genre> readAllGenres() throws SQLException, ClassNotFoundException {
		return read("SELECT * FROM tbl_genre", null);
	}
	
	

	public void addBookGenres(Integer bookId, Integer genreId) throws SQLException, ClassNotFoundException {
		save("INSERT INTO tbl_book_genres VALUES (?, ?)", new Object[] { bookId, genreId });
	}
	
	public void deleteBookGenres(Integer bookId) throws SQLException, ClassNotFoundException {
		save("DELETE FROM tbl_book_genres WHERE bookId = ?", new Object[] { bookId});
	}
	
	public void deletedBookGenre(Integer genreId) throws SQLException, ClassNotFoundException {
		save("DELETE FROM tbl_book_genres WHERE genre_id = ?", new Object[] { genreId});
	}
	
	
	public void updateGenre(Genre genre)throws ClassNotFoundException, SQLException {
		save("UPDATE tbl_genre SET genre_name = ? WHERE genre_id = ?",
				new Object[] { genre.getGenreName(), genre.getGenreId() });
	}
	public void deleteGenre(Genre genre)throws ClassNotFoundException, SQLException {
		save("DELETE FROM tbl_genre WHERE genre_id = ?", new Object[] { genre.getGenreId() });
	}
	
	public Integer addGenreWithPk(Genre genre) throws ClassNotFoundException, SQLException {
		return saveWithPk("INSERT INTO tbl_genre (genre_name) VALUES (?)", new Object[] { genre.getGenreName() });
	}
	
	public void addGenre(Genre genre) throws ClassNotFoundException, SQLException {
		 save("INSERT INTO tbl_genre (genre_name) VALUES (?)", new Object[] { genre.getGenreName() });
	}


	
	
	
	@Override
	public List<Genre> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {

		List<Genre> genres = new ArrayList<>();
		GenreDAO gdao = new GenreDAO(conn);
		while(rs.next()) {
			Genre g = new Genre(rs.getInt("genreId"), rs.getString("genreName"));
			genres.add(g);
		}
		return genres;
	}

}
