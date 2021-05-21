package com.fec.demo.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.security.core.userdetails.User;
import com.fec.demo.repository.IuserRepository;



@Service
public class JwtUserDetailsService implements UserDetailsService {
	@Autowired
	private IuserRepository repo;

	public UserDetails loadUserById(Long id) {
		com.fec.demo.entity.User user = repo.findOneById(id); // lấy thuôn tin user theo id
		if (user == null) {
			throw new UsernameNotFoundException("Không tìm thấy id jwtservice: " + id);
		}else {
			System.out.println("jwtdetail: token đâng đang nhập "+user.toString()); // lấy id user
			return new CustomUserDetails(user);
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//kiểm tra user có tồn tại trong database
		com.fec.demo.entity.User user = repo.findByPhonenumber(username);
		if (user == null) {
			throw new UsernameNotFoundException("Không tìm thấy username: " + username);
		}
		try {
			
//			System.out.println(user.toString());
//			String password = user.getPassword();
//			String []role = user.getRoleuser().stream()
//					.map(err->err.getRole().getId())
//					.collect(Collectors.toList()).toArray(new String[0]);
			
			return new CustomUserDetails(user);
//			return User.withUsername(username).password(password).build();
		
		} catch (Exception e) {
			throw new UsernameNotFoundException(username +"not found!");
		}
		

	}

}