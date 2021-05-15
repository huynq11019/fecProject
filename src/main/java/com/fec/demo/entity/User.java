package com.fec.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Data @Getter @Setter
public class User {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "fullname")
	private String fullname;
	@Column(name = "email")
	private String email;
	@Column(name = "password")
	private String password;
	@Column(name = "gender")
	private Boolean gender;
	@Column(name = "active")
	private Boolean active; // trạng thái hoạt động của tài khoản
	@Column(name = "ngaygianhap")
	private Date ngaygianhap;
	@Column(name = "avatar")
	private String avatar;
	@Column(name = "phonenumber")
	private String phonenumber;
	@Column(name="roleid")
	private int roleid;
	
}
