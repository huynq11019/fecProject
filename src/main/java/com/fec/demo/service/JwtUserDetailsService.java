package com.fec.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fec.demo.entity.Role;
import com.fec.demo.entity.User;
import com.fec.demo.repository.IuserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
@Service
public class JwtUserDetailsService implements UserDetailsService {
	@Autowired
	private IuserRepository repo;

	public UserDetails loadUserById(Long id) {
		System.out.println("id:" + id);
		User user = repo.findOneById(id); // lấy thuôn tin user theo id

		if (user == null) {
			throw new UsernameNotFoundException("Không tìm thấy id jwtservice: " + id);
		} else {
			System.out.println("29 jwtdetail: token đâng đang nhập " + user.toString()); // lấy id user
//			try {
//				if (user.getRoleuser().size() > 0) {
//					System.out.println("user detail+ " + user.getRoleuser());
////				System.out.println(user.getRoleuser().size());
//				}
//			} catch (Exception e) {
//				// TODO: handle exception
//				System.out.println("không đọc được role" + e);
//			}
			// converse
			 List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
			
			 for(Role role : user.getRoleuser()) {
				    System.out.println("44 jwt detail"+role.getName());
				    GrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
	                grantList.add(authority);
				}
			return new CustomUserDetails(user.getId(), user.getPhonenumber(), user.getPassword(), grantList);
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// kiểm tra user có tồn tại trong database
		User user = repo.findByPhonenumber(username);
		if (user == null) {
			throw new UsernameNotFoundException("Không tìm thấy username: " + username);
		}
		try {
			 List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
			 for(Role role : user.getRoleuser()) {
				    System.out.println("61 jwt detail "+role);
				    GrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
	                grantList.add(authority);
				}
			return new CustomUserDetails(user.getId(), user.getPhonenumber(), user.getPassword(), grantList);


		} catch (Exception e) {
			throw new UsernameNotFoundException(username + "not found!");
		}

	}

}