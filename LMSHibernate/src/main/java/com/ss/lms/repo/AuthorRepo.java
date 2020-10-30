package com.ss.lms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ss.lms.entity.Author;

@Repository
public interface AuthorRepo extends  JpaRepository<Author, Integer> {
	
	
	@Query(" FROM Author where authorName =:authorName")
	public List<Author> readAuthorsByName(@Param("authorName") String authorName);
	
	@Query(" FROM Author where authorId =:authorId")
	public List<Author> readAuthorsById(@Param("authorId") int authorId);

}
