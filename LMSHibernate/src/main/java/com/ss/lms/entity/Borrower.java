package com.ss.lms.entity;

/**
 * 
 */

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.JoinTable;
//import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

/**
 * @author Ian
 *
 */

@Entity
@Table(name = "tbl_borrower")
public class Borrower {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cardNo")
	private Integer cardNo;
	
	@Column(name = "name")
	@NonNull
	private String name;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "phone")
	private String phone;
	
//	public Borrower(Integer carNo, String name, String address, String phone) {
//		this.cardNo = carNo;
//		this.name = name;
//		this.address = address;
//		this.phone = phone;
//	}
	
	public Integer getCardNo() {
		return cardNo;
	}

	public void setCardNo(Integer cardNo) {
		this.cardNo = cardNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	

}
