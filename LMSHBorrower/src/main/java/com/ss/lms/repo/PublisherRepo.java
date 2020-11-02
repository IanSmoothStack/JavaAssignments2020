package com.ss.lms.repo;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.ss.lms.entity.Publisher;

public interface PublisherRepo extends  JpaRepository<Publisher, Integer> {
	
	@Query(" FROM Publisher where publisherName =:publisherName")
	public List<Publisher> readPublishersByName(@Param("publisherName") String publisherName);
	
	@Query(" FROM Publisher where publisherId =:publisherId")
	public List<Publisher> readPublishersById(@Param("publisherId") int publisherId);

}
