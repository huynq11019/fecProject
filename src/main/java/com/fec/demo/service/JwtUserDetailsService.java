package com.fec.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fec.demo.entity.User;
import com.fec.demo.repository.IuserRepository;



@Service
public class JwtUserDetailsService implements UserDetailsService {
	@Autowired
	private IuserRepository repo;
	public UserDetails loadUserById(Long id) {
		User user = repo.findOneById(id); // lấy thuôn tin user theo id
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
		System.out.println(user.toString());
		if (user == null) {
			throw new UsernameNotFoundException("Không tìm thấy username: " + username);
		}else {
			return new CustomUserDetails(user);
		}
//		if ("huyz".equals(username)) {
//			return new User("huyz", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
//					new ArrayList<>());
//		} else {
//			throw new UsernameNotFoundException("Không tìm thấy username: " + username);
//		}
	}

}