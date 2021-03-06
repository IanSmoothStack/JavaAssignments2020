/**
 * 
 */
package com.ss.exercise.entity;

import java.util.List;

/**
 * @author Ian
 *
 */
public class Borrower {

	
	private Integer cardNo;
	private String name;
	private String address;
	private String phone;
	
	public Borrower(Integer carNo, String name, String address, String phone) {
		this.cardNo = carNo;
		this.name = name;
		this.address = address;
		this.phone = phone;
	}
	
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
