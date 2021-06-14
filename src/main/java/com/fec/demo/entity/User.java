package com.fec.demo.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
	private Boolean isMale;
	@Column(name = "active")
	private Boolean active; // trạng thái hoạt động của tài khoản
	@Column(name = "ngaygianhap")
	private Date ngaygianhap;
	@Column(name = "avatar")
	private String avatar;
	@Column(name = "phonenumber")
	private String phonenumber;
	@Column(name = "birthday")
	private Long birthday ;
	
//	@JsonIgnore
//	 @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	// Quan hệ n-n với đối tượng ở dưới (User) (1 người có nhiều role)
//	@EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
//	@ToString.Exclude // Khoonhg sử dụng trong toString()
//	@JoinTable(name = "userrole", // tạo một join table có tên là userrole
//			joinColumns = { @JoinColumn(name = "userid") } // TRong đó, khóa ngoại chính là userid trỏ tới class hiện tại (user)
//	, inverseJoinColumns = { @JoinColumn(name = "roleid") })
	@JsonIgnore
	@OneToMany(mappedBy = "users", fetch = FetchType.EAGER)
	 private List<UserRole> roleuser ;
	@Override
	public String toString() {
		return "User [id=" + id + ", fullname=" + fullname + ", email=" + email + ", password=" + password + ", isMale="
				+ isMale + ", active=" + active + ", ngaygianhap=" + ngaygianhap + ", avatar=" + avatar
				+ ", phonenumber=" + phonenumber + "]";
	}
	
	@Transient
	public List<GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		if (this.getRoleuser()!= null) {
			for (UserRole usersRoles:this.roleuser) {
				authorities.add(new SimpleGrantedAuthority(usersRoles.getRoles().getName()));
			}
		}
		
		return authorities;
	}

}
