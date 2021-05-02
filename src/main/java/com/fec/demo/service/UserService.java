package com.fec.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fec.demo.entity.User;
import com.fec.demo.repository.IuserRepository;

@Service // chỉ thị đây là 1 service
@Transactional
public class UserService {
	@Autowired
	private IuserRepository repo;

	// lấy danh sách user
	public List<User> listAll() {
		return repo.findAll();
	}

	// thêm bản ghi
	public User saveUser(User model) {
		// giá trị truyền vào không được là null
		
		return repo.save(model);
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
	
	};
	// tạo them phương thức đăng nhập
	// phương thức actiive
}
