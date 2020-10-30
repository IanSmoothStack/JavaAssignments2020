package com.ss.lms.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.lms.entity.Borrower;
import com.ss.lms.repo.BorrowerRepo;

@RestController
public class BorrowerService {
	
//	@Autowired
//	public BorrowerDAO bdao;	
	
	@Autowired
	BorrowerRepo brepo;

	
	
	
	
	@RequestMapping(value = "/getBorrowersByQuery", method = RequestMethod.GET, produces = "application/json")
	public List<Borrower> getBorrowersByQuery(@RequestParam String searchString) {
		List<Borrower> borrowers = new ArrayList<>();
		if (searchString != null && searchString.length() > 0) {
				borrowers = brepo.readBorrowersByName(searchString);
		} else {
				borrowers = brepo.findAll();
		}
		return borrowers;
	}
	
	
//	@Transactional
	@RequestMapping(value = "/getAllBorrowers", method = RequestMethod.GET, produces = "application/json")
	public List<Borrower> getAllBorrowers() {
		List<Borrower> borrowers = new ArrayList<>();
		borrowers = brepo.findAll();
		return borrowers;
	}

}
