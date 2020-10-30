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

import com.ss.lms.entity.Branch;

@RestController
public class LibrarianService {
	
//	@Autowired
//	public BranchDAO bdao;	
	
//	@Transactional
	@RequestMapping(value = "/getAllBranches", method = RequestMethod.GET, produces = "application/json")
	public List<Branch> getAllBranches() {
//		try {
//		
//				return bdao.readAllBranches();
//			
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//			return null;
//		}
		return null;
	}

}
