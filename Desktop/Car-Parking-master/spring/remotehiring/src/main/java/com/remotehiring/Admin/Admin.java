package com.remotehiring.Admin;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer adminid;
	private String password;
	
	public Admin() {
	}

	public Admin(Integer adminid, String password) {
		
		this.adminid = adminid;
		this.password = password;
	}

	public Integer getAdminid() {
		return adminid;
	}

	public void setAdminId(Integer adminid) {
		this.adminid = adminid;
	}

	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
	
}
