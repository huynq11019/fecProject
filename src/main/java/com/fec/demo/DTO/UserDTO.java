package com.fec.demo.DTO;

import java.util.Date;

import lombok.Data;
@Data
public class UserDTO {
	
	private Long id;
	private String fullname;
	private String email;
	private String password;
	private Boolean gender;
	private Boolean active;// trạng kích hoạt của tài khoản
	private Date ngaygianhap;
	private String avatar;
	private Integer roleid;
	private String phonenumber;
	

}
