/**
 * 
 */
package com.ss.exercise.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ss.exercise.dao.AuthorDAO;
import com.ss.exercise.dao.BookDAO;
import com.ss.exercise.dao.BorrowerDAO;
import com.ss.exercise.dao.BranchDAO;
import com.ss.exercise.dao.GenreDAO;
import com.ss.exercise.dao.PublisherDAO;
import com.ss.exercise.entity.Author;
import com.ss.exercise.entity.Book;
import com.ss.exercise.entity.Borrower;
import com.ss.exercise.entity.Genre;
import com.ss.exercise.entity.Publisher;

/**
 * @author Ian
 *
 */
public class AdministratorService {
	public ConnectionUtil conUtil = new ConnectionUtil();

	public String addBook(Book book) throws SQLException {
		Connection conn = null;
		try {
			conn = conUtil.getConnection();
			BookDAO bdao = new BookDAO(conn);
			///Try not calling all these
			//Just add them in book
			//AuthorDAO adao = new AuthorDAO(conn);
			//GenreDAO gdao = new GenreDAO(conn);
			//PublisherDAO pdao = new PublisherDAO(conn);
			if (book.getTitle() != null && book.getTitle().length() > 45) {
				return "Book Title cannot be empty and should be 45 char in length";
			}
			book.setBookId(bdao.addBookWithPk(book));
//			book.setBookId(bdao.addBookWithPk2(book));
			//Do I even need this or can I just update the whole book at once?
			if(!book.getAuthors().isEmpty())
			for (Author a : book.getAuthors()) {
				//adao.addBookAuthors(book.getBookId(), a.getAuthorId());
				bdao.addBookAuthors(book.getBookId(), a.getAuthorId());
		
			}
			
			if(!book.getGenres().isEmpty())
				for( Genre g: book.getGenres()) {
					//gdao.addBookGenres(book.getBookId(), g.getGenreId());
					bdao.addBookGenres(book.getBookId(), g.getGenreId());
				}
			
			///set publisher here?
//			if(book.getPublisher()!=null)
//				pdao.addBookPublishers(book.getBookId(), book.getPubId());
			// Do the same for genres/branches etc.
			// for(Author a: book.getAuthors()) {
			// adao.addBookAuthors(book.getBookId(), a.getAuthorId());
			// }
			conn.commit();
			return "Book added sucessfully";
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			if (conn != null) {
				conn.rollback();
			}
			return "Unable to add book - contact admin.";
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	

	
	
	
	

	public List<Book> getBooks(String searchString) {
		try(Connection conn = conUtil.getConnection()) {
			BookDAO bdao = new BookDAO(conn);
			if (searchString != null) {
				return bdao.readAllBooksByName(searchString);
			} else {
				return bdao.readAllBooks();
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Publisher> getPublishers(String searchString) {
		try(Connection conn = conUtil.getConnection()) {
			PublisherDAO pdao = new PublisherDAO(conn);
			if (searchString != null) {
				return pdao.readAllPublishers(searchString);
			} else {
				return pdao.readAllPublishers();
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public List<Publisher> getBookPublishers() {
		try(Connection conn = conUtil.getConnection()) {
			PublisherDAO pdao = new PublisherDAO(conn);
				return pdao.readAllBookPublishers();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public List<Author> getAuthors(String searchString) {
		try(Connection conn = conUtil.getConnection()) {
			AuthorDAO adao = new AuthorDAO(conn);
			if (searchString != null) {
				return adao.readAllAuthorsByName(searchString);
			} else {
				return adao.readAllAuthors();
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Genre> getGenres(){
		try(Connection conn = conUtil.getConnection()) {
			GenreDAO gdao = new GenreDAO(conn);
			return gdao.readAllGenres();
		}catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public void updateBookName(Book bBook) {
		try(Connection conn = conUtil.getConnection()) {
			BookDAO bdao = new BookDAO(conn);
				 bdao.updateBook(bBook);
				 conn.commit();
				 System.out.println("Update successful");
				 
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			
		}
	}
	
	public void updateBookAll(Book book) {
		try(Connection conn = conUtil.getConnection()) {
			BookDAO bdao = new BookDAO(conn);
			AuthorDAO adao = new AuthorDAO(conn);
			GenreDAO gdao = new GenreDAO(conn);
			int bookId = book.getBookId();
			PublisherDAO pdao = new PublisherDAO(conn);
				 bdao.updateBook(book);
				 if(!book.getAuthors().isEmpty()) {
					 adao.deleteBookAuthors(bookId);
						for (Author a : book.getAuthors()) {
							adao.addBookAuthors(book.getBookId(), a.getAuthorId());
						}
					
						}
						if(!book.getGenres().isEmpty()) {
							gdao.deleteBookGenres(bookId);
							for( Genre g: book.getGenres()) {
								gdao.addBookGenres(book.getBookId(), g.getGenreId());
							}
						}
						
						if(book.getPublisher()!=null) {
							pdao.deleteBookPublishers(bookId);
							pdao.addBookPublishers(book.getBookId(), book.getPubId());
						}
				 conn.commit();
				 System.out.println("Update successful");
				 
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			
		}
	}
	
	

	
	
	
	public void deleteBook(Book book) {
		try(Connection conn = conUtil.getConnection()) {
			BookDAO bdao = new BookDAO(conn);
			AuthorDAO adao = new AuthorDAO(conn);
			GenreDAO gdao = new GenreDAO(conn);
			PublisherDAO pdao = new PublisherDAO(conn);
			int bookId = book.getBookId();
				adao.deleteBookAuthors(bookId);
				gdao.deleteBookGenres(bookId);
				pdao.deleteBookPublishers(bookId);
				 bdao.deleteBook(book);
				 conn.commit();
				 System.out.println("Delete successful");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			
		}
	}
	
	
	public Author getAuthorById(int authorId) {
		try(Connection conn = conUtil.getConnection()) {
			AuthorDAO adao = new AuthorDAO(conn);
				List<Author> authors =adao.readAuthorsById(authorId);
				if(!authors.isEmpty()) {
					return authors.get(0);
				}
				
				return null;
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public void deleteAuthor(Author author) {
		try(Connection conn = conUtil.getConnection()) {
			AuthorDAO adao = new AuthorDAO(conn);
				adao.deletedBookAuthor(author.getAuthorId());
				 adao.deleteAuthor(author);
				 conn.commit();
				 System.out.println("Delete successful");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			
		}
	}
	public void addAuthor(Author author) {
		try(Connection conn = conUtil.getConnection()) {
			AuthorDAO adao = new AuthorDAO(conn);
				// adao.addAuthor(author);
				 author.setAuthorId(adao.addAuthorWithPk(author));
				 conn.commit();
				 System.out.println("Additon successful");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			
		}
	}
	
	public void updateAuthor(Author author) {
		try(Connection conn = conUtil.getConnection()) {
			AuthorDAO adao = new AuthorDAO(conn);
				 adao.updateAuthor(author);
				 conn.commit();
				 System.out.println("Update successful");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			
		}
	}
	
	
	
	
	public void deleteGenre(Genre genre) {
		try(Connection conn = conUtil.getConnection()) {
			GenreDAO adao = new GenreDAO(conn);
				adao.deletedBookGenre(genre.getGenreId());
				 adao.deleteGenre(genre);
				 conn.commit();
				 System.out.println("Delete successful");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			
		}
	}
	public void addGenre(Genre genre) {
		try(Connection conn = conUtil.getConnection()) {
			GenreDAO adao = new GenreDAO(conn);
				// adao.addGenre(genre);
				 genre.setGenreId(adao.addGenreWithPk(genre));
				 conn.commit();
				 System.out.println("Additon successful");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			
		}
	}
	
	public void updateGenre(Genre genre) {
		try(Connection conn = conUtil.getConnection()) {
			GenreDAO adao = new GenreDAO(conn);
				 adao.updateGenre(genre);
				 conn.commit();
				 System.out.println("Update successful");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			
		}
	}
	
	
	
	public void deletePublisher(Publisher publisher) {
		try(Connection conn = conUtil.getConnection()) {
			PublisherDAO adao = new PublisherDAO(conn);
			//adao.deletedBookPublisher(publisher.getPublisherId());
			adao.deletePublisher(publisher);
				 conn.commit();
				 System.out.println("Delete successful");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			
		}
	}
	public void addPublisher(Publisher publisher) {
		try(Connection conn = conUtil.getConnection()) {
			PublisherDAO adao = new PublisherDAO(conn);
				// adao.addPublisher(publisher);
				 publisher.setPublisherId(adao.addPublisherWithPk(publisher));
				 conn.commit();
				 System.out.println("Additon successful");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			
		}
	}
	
	public void updatePublisher(Publisher publisher) {
		try(Connection conn = conUtil.getConnection()) {
			PublisherDAO adao = new PublisherDAO(conn);
				 adao.updatePublisher(publisher);
				 conn.commit();
				 System.out.println("Update successful");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			
		}
	}
	
	
	
	
	public void deleteBorrower(Borrower borrower) {
		try(Connection conn = conUtil.getConnection()) {
			BorrowerDAO adao = new BorrowerDAO(conn);
				 adao.deleteBorrower(borrower);
				 conn.commit();
				 System.out.println("Delete successful");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			
		}
	}
	public void addBorrower(Borrower borrower) {
		try(Connection conn = conUtil.getConnection()) {
			BorrowerDAO adao = new BorrowerDAO(conn);
				// adao.addBorrower(borrower);
				 borrower.setCardNo(adao.addBorrowerWithPk(borrower));
				 conn.commit();
				 adao.updateBorrower(borrower);
				 adao.updatePhoneBorrower(borrower);
				 adao.updateAddressBorrower(borrower);
				 conn.commit();
				 System.out.println("Additon successful");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			
		}
	}
	
	public void updateBorrower(Borrower borrower) {
		try(Connection conn = conUtil.getConnection()) {
			BorrowerDAO adao = new BorrowerDAO(conn);
				 adao.updateBorrower(borrower);
				 adao.updatePhoneBorrower(borrower);
				 adao.updateAddressBorrower(borrower);
				 conn.commit();
				 System.out.println("Update successful");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			
		}
	}
	public List<Borrower> readAllBorrowers() {
		try(Connection conn = conUtil.getConnection()) {
			BorrowerDAO adao = new BorrowerDAO(conn);
				 return adao.readAllBorrowers();
				
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
			
		}
	}

}