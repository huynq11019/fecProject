package com.fec.demo.service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fec.demo.entity.Role;
import com.fec.demo.entity.User;
import com.fec.demo.repository.IuserRepository;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {
//	  User user;

	private Long userId;
	private String username;// phonenumber
	private String password;
//	private Set<Role> authorities;
	private List<GrantedAuthority> grantList ;
	
//	 private Collection<? extends GrantedAuthority> roles;
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
//		System.out.println("custom Userdetail: "+authorities.toArray()[0]); 
	
	
//		return Collections.singleton(grantList);
		return grantList;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}



}
