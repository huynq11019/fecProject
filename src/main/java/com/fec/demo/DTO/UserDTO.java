package com.fec.demo.DTO;

import java.util.Date;

public class UserDTO {

	private long id;

	private String fullname;

	private String email;

	private String password;

	private boolean gender;

	private boolean active; // trạng thái hoạt động của tài khoản

	private Date ngaygianhap;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public Date getNgaygianhap() {
		return ngaygianhap;
	}

	public void setNgaygianhap(Date ngaygianhap) {
		this.ngaygianhap = ngaygianhap;
	}


	
}
