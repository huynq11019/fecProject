package com.fec.demo.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data 
@Entity
@Table(name = "users")

public class User {
	@Id
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
//	@JsonIgnore
//	@OneToMany(mappedBy = "user")
//	private List<UserRole> roleuser;
	
	
}
