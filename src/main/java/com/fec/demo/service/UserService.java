package com.fec.demo.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fec.demo.DTO.ParentOutput;
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
	public ParentOutput<User> listAll(int page, int limit, String sortBy, boolean order) {
//		System.out.println(page +""+ limit);

		Direction direction = Direction.ASC;
		if (order) {
			direction = Direction.DESC;
		}
		Sort sort = Sort.by(direction, sortBy);
		Pageable paging = PageRequest.of(page, limit, sort);
		ParentOutput<User> result = new ParentOutput<User>();
		result.setPage(page);
		result.setListResult(repo.findAll(paging).getContent());
		result.setTotalPage(pageTotal());

		return result;
	}

//get total item
	public int pageTotal() {
		return (int) repo.count();
	}

	// thêm bản ghi
	public User saveUser(User model) {
		// hiển thị token
		//System.out.println(token);
		// chuyển token sang id và lấy dối tượng
		// nếu id là null thì tạo moi còn không thì update
		if (model.getId() == null) {
			model.setPassword(passwordEncoder.encode(model.getPassword()));
//			 kiểm tra sự tồn tại của user
			if(repo.findByPhonenumber(model.getPhonenumber())!= null)
			{
				
				return null;
			}
			return repo.save(model);
		} else {
			// kiểm tra có phải admin hay là người dùng hiện tại không

			{
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
				} else {
					model.setPassword(passwordEncoder.encode(model.getPassword()));
				}
				if (model.getIsMale() == null) {
					model.setIsMale(oldUser.getIsMale());
				}
				if (model.getPhonenumber() == null) {
					model.setPhonenumber(oldUser.getPhonenumber());
				}
				if (model.getAvatar() == null) {
					model.setAvatar(oldUser.getAvatar());
				}
				if (model.getPhonenumber() == null) {
					model.setPhonenumber(oldUser.getPhonenumber());
				}
				if (model.getAvatar() == null) {
					model.setAvatar(oldUser.getAvatar());
				}
				// trạng thái active không được thay đổi
				model.setActive(oldUser.getActive());
				model.setNgaygianhap(oldUser.getNgaygianhap());
				System.out.println(model.toString());
				return repo.save(model);
			}

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
		User u = repo.findOneById(id);
//		if (u.getRoleuser().size()>0) {
//			System.out.println("user service+ "+ u.getRoleuser());
//		}

		return u;

	}

	public User activeUser(Long idUser, boolean isActive) {
		User oldUser = getByid(idUser);
		oldUser.setActive(isActive);
		return repo.save(oldUser);
	}

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// kiểm tra User có tồn tại trong database không
		User user = repo.findByPhonenumber(username);
		return null;
	}

	// phương thức actiive
	// tìm kiếm người dùng
}
