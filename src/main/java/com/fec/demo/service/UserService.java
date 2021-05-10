package com.fec.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fec.demo.entity.User;
import com.fec.demo.repository.IuserRepository;

@Service // chỉ thị đây là 1 service
@Transactional
public class UserService {
	@Autowired
	private IuserRepository repo;
	  @Autowired
	    PasswordEncoder passwordEncoder;
	// lấy danh sách user
	public List<User> listAll() {
		return repo.findAll();
	}

	// thêm bản ghi
	public User saveUser(User model) {
		// nếu id là null thì tạo moi còn không thì update
		if (model.getId() == null) {
			model.setPassword(passwordEncoder.encode(model.getPassword()));
			return repo.save(model);
		} else {
			User oldUser = getByid(model.getId());
			// chèn dữ liệu vào bản ghi mới
			if (model.getFullname() == null) {
				model.setFullname(oldUser.getFullname());
			}
			if (model.getEmail() == null) {
				model.setEmail(oldUser.getEmail());
			}
			if (model.getPassword() == null) {
				model.setPassword(oldUser.getPassword());
			}else {
				model.setPassword(passwordEncoder.encode(model.getPassword()));
			}
			if (model.getGender() == null) {
				model.setGender(oldUser.getGender());
			}
			if (model.getPhonenumber() == null) {
				model.setPhonenumber(oldUser.getPhonenumber());
			}
			if (model.getAvatar() == null) {
				model.setAvatar(oldUser.getAvatar());
			}
			if(model.getPhonenumber() == null) {
				model.setPhonenumber(oldUser.getPhonenumber());
			}
			if (model.getAvatar() == null ) {
				model.setAvatar(oldUser.getAvatar());
			}
			// trạng thái active không được thay đổi
			model.setActive(oldUser.getActive());
			model.setNgaygianhap(oldUser.getNgaygianhap());
			System.out.println(model.toString());
			return repo.save(model);

		}

	}

	// xóa bản ghi của user Nếu xóa khong thành công thì return -1
	public long delete(Long id) {
		try {
			repo.deleteById(id);
			return id;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("xóa user không thành công userService" + e.getMessage());
			return -1;
		}
	}

	public User getByid(Long id) {
		return repo.findById(id).get();

	}

	public void update(User obj) {

	}


	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//kiểm tra User có tồn tại trong database không
	User user=	repo.findByPhonenumber(username);
		return null;
	}

	// tạo them phương thức đăng nhập
	// phương thức actiive
	// tìm kiếm người dùng
}
