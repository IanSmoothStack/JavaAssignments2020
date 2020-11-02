package com.ss.lms.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//import org.hibernate.mapping.Set;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.ss.lms.entity.Author;
import com.ss.lms.entity.Book;
import com.ss.lms.entity.Borrower;
import com.ss.lms.entity.Branch;
import com.ss.lms.entity.Genre;
import com.ss.lms.entity.BookLoans;
import com.ss.lms.entity.Publisher;
import com.ss.lms.repo.AuthorRepo;
import com.ss.lms.repo.BookLoansRepo;
import com.ss.lms.repo.BookRepo;
import com.ss.lms.repo.BorrowerRepo;
import com.ss.lms.repo.BranchRepo;
import com.ss.lms.repo.GenreRepo;
import com.ss.lms.repo.PublisherRepo;

@RestController
public class AdministratorService {

	
	@Autowired
	BookRepo brepo;
	
	@Autowired
	AuthorRepo arepo;
	
	@Autowired
	GenreRepo grepo;

	@Autowired
	PublisherRepo prepo;
	
	@Autowired
	BranchRepo brrepo;
	
	@Autowired
	BorrowerRepo borepo;
	
	@Autowired
	public BookLoansRepo blrepo;

	@Transactional
	@RequestMapping(value = "/addBook", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public List<Book> addBook(@RequestBody Book book) throws SQLException {
		

			
		List<Author> authors = new ArrayList<>();
		if(!book.getAuthors().isEmpty())
		for (Author a : book.getAuthors()) {
			List<Author> tempAuthors = new ArrayList<>();
			int authorId = a.getAuthorId();
			tempAuthors= arepo.readAuthorsById(authorId);
			if(!tempAuthors.isEmpty())
				authors.add(tempAuthors.get(0));
		}
		book.setAuthors(authors);
		
		
		
		List<Genre> genres = new ArrayList<>();
		if(!book.getGenres().isEmpty())
		for (Genre g : book.getGenres()) {
			List<Genre> tempGenres = new ArrayList<>();
			int genreId = g.getGenreId();
			tempGenres= grepo.readGenresById(genreId);
			if(!tempGenres.isEmpty())
				genres.add(tempGenres.get(0));
		}
		book.setGenres(genres);
		
		

			brepo.save(book);
			return getAllBooks();
		
	}

	@RequestMapping(value = "/getBooksByQuery", method = RequestMethod.GET, produces = "application/json")
	public List<Book> getBooksByQuery(@RequestParam String searchString) {
		List<Book> books = new ArrayList<>();
		if (searchString != null && searchString.length() > 0) {
				books = brepo.readBooksByTitle(searchString);
		} else {
				books = brepo.findAll();
		}
		return books;
	}
	
	
	@RequestMapping(value = "/getAllBooks", method = RequestMethod.GET, produces = "application/json")
	public List<Book> getAllBooks() {
		List<Book> books = new ArrayList<>();
		books = brepo.findAll();
		return books;
	}
	
	@RequestMapping(value = "/getAllGenres", method = RequestMethod.GET, produces = "application/json")
	public List<Genre> getAllGenres() {
		List<Genre> genres = new ArrayList<>();
		genres = grepo.findAll();
		return genres;
	}
	
	@RequestMapping(value = "/getAllAuthors", method = RequestMethod.GET, produces = "application/json")
	public List<Author> getAllAuthors() {
		List<Author> authors = new ArrayList<>();
		authors = arepo.findAll();
		return authors;
	}
	
	@RequestMapping(value = "/getAllPublishers", method = RequestMethod.GET, produces = "application/json")
	public List<Publisher> getAllPublishers() {
		List<Publisher> publishers = new ArrayList<>();
		publishers = prepo.findAll();
		return publishers;
	}
	
	
	@RequestMapping(value = "/getBorrowersByQuery", method = RequestMethod.GET, produces = "application/json")
	public List<Borrower> getBorrowersByQuery(@RequestParam String searchString) {
		List<Borrower> borrowers = new ArrayList<>();
		if (searchString != null && searchString.length() > 0) {
				borrowers = borepo.readBorrowersByName(searchString);
		} else {
				borrowers = borepo.findAll();
		}
		return borrowers;
	}
	
	
	@RequestMapping(value = "/getAllBorrowers", method = RequestMethod.GET, produces = "application/json")
	public List<Borrower> getAllBorrowers() {
		List<Borrower> borrowers = new ArrayList<>();
		borrowers = borepo.findAll();
		return borrowers;
	}
	
	
	
	@Transactional
	@RequestMapping(value = "/addGenre", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public List<Genre> addGenre(@RequestBody Genre genre) throws SQLException { 
		
				grepo.save(genre);
				 System.out.println("Addition successful");
				 return(getAllGenres());
		
	}
	
	@Transactional
	@RequestMapping(value = "/addBorrower", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public List<Borrower> addBorrower(@RequestBody Borrower borrower) throws SQLException { 
		
				borepo.save(borrower);
		
				 return(getAllBorrowers());
		
	}
	
	
	

	
	@Transactional
	@RequestMapping(value = "/deleteBook", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public List<Book> deleteBook(@RequestBody Book book) throws SQLException { 
		
				brepo.delete(book);
				 return(getAllBooks());
		
	}
	
	@Transactional
	@RequestMapping(value = "/updateBook", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public List<Book> updateBook(@RequestBody Book book) throws SQLException { 
		
				if(book.getBookId()== null)
					return(getAllBooks());
				
				Book oldBook = getBookById(book.getBookId());
				
				if(book.getAuthors()!= null) {
					List<Author> authors = new ArrayList<>();
					for (Author a : book.getAuthors()) {
						authors.add(getAuthorById(a.getAuthorId()));
					}
					oldBook.setAuthors(authors);
				}
				
				if(book.getGenres() != null) {
					List<Genre> genres = new ArrayList<>();
					for (Genre g : book.getGenres()) {
						genres.add(getGenreById(g.getGenreId()));
					}
					oldBook.setGenres(book.getGenres());
				}
				
				if(book.getPublisher()!=null)
					oldBook.setPublisher(getPublisherById(book.getPubId()));
				
				if(book.getTitle()!=null)
					oldBook.setTitle(book.getTitle());
				
				brepo.save(oldBook);

		
				 return(getAllBooks());
		
	}
	
	
	
	

	
	
	@Transactional
	@RequestMapping(value = "/deleteBookById", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public List<Book> deleteBookById(@RequestBody Book book) throws SQLException { 
		
				if(book.getBookId()== null)
					return getAllBooks();
				
				Book b = getBookById(book.getBookId());
				brepo.delete(b);
				 return(getAllBooks());
		
	}
	
	@Transactional
	@RequestMapping(value = "/deleteAuthor", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public List<Author> deleteAuthor(@RequestBody Author author) throws SQLException { 
		
				arepo.delete(author);
				 return(getAllAuthors());
		
	}
	
	@Transactional
	@RequestMapping(value = "/deleteAuthorById", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public List<Author> deleteAuthorById(@RequestBody Author author) throws SQLException { 
		
				if(author.getAuthorId()== null)
					return getAllAuthors();
				
				Author a = getAuthorById(author.getAuthorId());
				arepo.delete(a);
				 return(getAllAuthors());
		
	}
	
	
	@Transactional
	@RequestMapping(value = "/deleteGenre", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public List<Genre> deleteGenre(@RequestBody Genre genre) throws SQLException { 
		
				grepo.delete(genre);
				 return(getAllGenres());
		
	}
	
	@Transactional
	@RequestMapping(value = "/deleteGenreById", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public List<Genre> deleteGenreById(@RequestBody Genre genre) throws SQLException { 
		
				if(genre.getGenreId()== null)
					return getAllGenres();
				
				grepo.deleteGenre(genre.getGenreId());
				 return(getAllGenres());
		
	}
	
	@Transactional
	@RequestMapping(value = "/updateGenreById", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public List<Genre> updateGenreById(@RequestBody Genre genre) throws SQLException { 
		
				if(genre.getGenreId()== null)
					return getAllGenres();
				
				grepo.updateGenre(genre.getGenreId(), genre.getGenreName());
				 return(getAllGenres());
		
	}
	
	@Transactional
	@RequestMapping(value = "/updateGenre", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public List<Genre> updateGenre(@RequestBody Genre genre) throws SQLException { 
		
				if(genre.getGenreId()== null)
					return getAllGenres();
				if(genre.getGenreName()!= null) {
					grepo.save(genre);
				}
				
				 return(getAllGenres());
		
	}
	
	
	@Transactional
	@RequestMapping(value = "/updateAuthor", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public List<Author> updateAuthor(@RequestBody Author author) throws SQLException { 
		
		if(author.getAuthorId() == null)
			return(getAllAuthors());
		
		
		Author oldAuthor = getAuthorById(author.getAuthorId());
		
		if(author.getAuthorName()!= null) {
			oldAuthor.setAuthorName(author.getAuthorName());
		}
		
		if(author.getBooks()!= null) {
			List<Book> books = new ArrayList<>();
			for (Book a : author.getBooks()) {
				books.add(getBookById(a.getBookId()));
			}
			oldAuthor.setBooks(books);
		}
		
				arepo.save(oldAuthor);
				 return(getAllAuthors());
		
	}
	
	
	@Transactional
	@RequestMapping(value = "/updatePublisher", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public List<Publisher> updatePublisher(@RequestBody Publisher publisher) throws SQLException { 
		
		if(publisher.getPublisherId() == null)
			return(getAllPublishers());
		
		
		Publisher oldPublisher = getPublisherById(publisher.getPublisherId());
		
		if(publisher.getPublisherName()!= null) {
			oldPublisher.setPublisherName(publisher.getPublisherName());
		}
		
		if(publisher.getBooks()!= null) {
			List<Book> books = new ArrayList<>();
			for (Book a : publisher.getBooks()) {
				books.add(getBookById(a.getBookId()));
			}
			oldPublisher.setBooks(books);
		}
		
		if(publisher.getPublisherAddress()!= null) {
			oldPublisher.setPublisherAddress(publisher.getPublisherAddress());
		}
		
				prepo.save(oldPublisher);
				 return(getAllPublishers());
		
	}
	
	
	@Transactional
	@RequestMapping(value = "/deletePublisher", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public List<Publisher> deletePublisher(@RequestBody Publisher publisher) throws SQLException { 
		
				prepo.delete(publisher);
				 return(getAllPublishers());
		
	}
	
	
	@Transactional
	@RequestMapping(value = "/deletePublisherById", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public List<Publisher> deletePublisherById(@RequestBody Publisher publisher) throws SQLException { 
		
				if(publisher.getPublisherId()== null)
					return getAllPublishers();
				
				Publisher p = getPublisherById(publisher.getPublisherId());
				prepo.delete(p);
				 return(getAllPublishers());
		
	}
	
	
	
	@Transactional
	@RequestMapping(value = "/deleteBorrower", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public List<Borrower> deleteBorrower(@RequestBody Borrower borrower) throws SQLException { 
		
				borepo.delete(borrower);
				 return(getAllBorrowers());
		
	}
	
	
	@Transactional
	@RequestMapping(value = "/deleteBorrowerById", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public List<Borrower> deleteBorrowerById(@RequestBody Borrower borrower) throws SQLException { 
		
				if(borrower.getCardNo()== null)
					return getAllBorrowers();
				
				Borrower p = getBorrowerById(borrower.getCardNo());
				borepo.delete(p);
				 return(getAllBorrowers());
		
	}
	
	
	@Transactional
	@RequestMapping(value = "/updateBorrowerById", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public List<Borrower> updateBorrowerById(@RequestBody Borrower borrower) throws SQLException { 
		
				if(borrower.getCardNo()== null)
					return getAllBorrowers();
				
				
				Borrower oldB = getBorrowerById(borrower.getCardNo());
				if(borrower.getName()!= null) {
					oldB.setName(borrower.getName());
				}
				if(borrower.getAddress()!= null) {
					oldB.setAddress(borrower.getAddress());
				}
				if(borrower.getPhone()!= null) {
					oldB.setPhone(borrower.getPhone());
				}
				borepo.save(oldB);
				 return(getAllBorrowers());
		
	}
	
	
	///meant for overriding due date but can be used to change any of the loan's values
	@RequestMapping(value = "/overrideBookLoan", method = RequestMethod.GET, produces = "application/json")
	public List<BookLoans> overrideBookLoan(@RequestBody BookLoans bookLoans) throws SQLException { 
		
		LibrarianService ls = new LibrarianService();
		if(bookLoans.getId().getBookId() == null|bookLoans.getId().getBranchId() == null |bookLoans.getId().getCardNo() == null)
			return null;
		int bookId = bookLoans.getId().getBookId();
		int branchId = bookLoans.getId().getBranchId();
		int cardNo = bookLoans.getId().getCardNo();
		BookLoans oldLoan = ls.getBookLoansById(bookId, branchId, cardNo);
		
		if(bookLoans.getDateIn()!=null) {
			oldLoan.setDateIn(bookLoans.getDateIn());
		}
		if(bookLoans.getDateOut()!=null) {
			oldLoan.setDateOut(bookLoans.getDateOut());
		}

		if(bookLoans.getDueDate()!=null) {
			oldLoan.setDueDate(bookLoans.getDueDate());
		}
		
		blrepo.save(bookLoans);
		
		return ls.getAllBookLoans();
	}
	
	
	
	
	
	
	
	
	
	public Book getBookById(int bookId) throws SQLException{
		List<Book> books = brepo.readBooksById(bookId);
		if(!books.isEmpty())
			return books.get(0);
		return null;
		
	}
	
	
	public Publisher getPublisherById(int publisherId) throws SQLException{
		List<Publisher> publishers = prepo.readPublishersById(publisherId);
		if(!publishers.isEmpty())
			return publishers.get(0);
		return null;
		
	}
	
	public Genre getGenreById(int genreId) throws SQLException{
		List<Genre> genres = grepo.readGenresById(genreId);
		if(!genres.isEmpty())
			return genres.get(0);
		return null;
		
	}
	
	public Author getAuthorById(int authorId) throws SQLException{
		List<Author> authors = arepo.readAuthorsById(authorId);
		if(!authors.isEmpty())
			return authors.get(0);
		return null;
		
	}
	
	
	public Branch getBranchById(int branchId) throws SQLException{
		List<Branch> branchs = brrepo.readBranchesById(branchId);
		if(!branchs.isEmpty())
			return branchs.get(0);
		return null;
		
	}
	
	public Borrower getBorrowerById(int cardNo) throws SQLException{
		List<Borrower> borrowers = borepo.readBorrowersByCardNo(cardNo);
		if(!borrowers.isEmpty())
			return borrowers.get(0);
		return null;
		
	}
	
	
	




}