package com.fec.demo.api;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fec.demo.DTO.ParentOutput;
import com.fec.demo.entity.User;
import com.fec.demo.service.UserService;

@CrossOrigin
@RestController
@RequestMapping
public class userAPI {
	@Autowired
	private UserService uService;

	@GetMapping(value = "/user")
	public ParentOutput<User> finAll(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "3") int limit, @RequestParam(defaultValue = "id") String sorby,
			@RequestParam(defaultValue = "false") boolean order) {

		return uService.listAll(page, limit, sorby, order);
	}

	// create User; không truyền khóa chính
	// đăng ký tài khoản mặc định khi đăng ký trạng thái sẽ là false

	@PostMapping(value = "/dangky")
	public User createUser(@RequestBody User obj) {
		System.out.println(obj.toString());
		obj.setId(null);
		obj.setNgaygianhap(new Date());
		obj.setActive(false); // khi mới đăng ký thì trạng thái của tài khoản là false
		System.out.println(obj.toString());
		return uService.saveUser(obj, null); // token == null

	}

	// tìm user theo mã
	@GetMapping(value = "/user/{id}")
//	@PreAuthorize("@appAuthorizer.authorize(authentication, 'FIND', this)")
	public User getUser(@PathVariable(name = "id") Long id) {
		return uService.getByid(id);
	}

	// xóa tài khoản trả về một id đã bị xóa
//	 @PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(value = "/admin/user/{id}")

	public long deleUser(@PathVariable(name = "id") Long id) {

		System.out.println("id vừa nhập vào "+id);
		return uService.delete(id);
//		return -1;
	}

	// cập nhật user theo id
//	@PreAuthorize("isAuthenticated")
	@PutMapping(value = "/admin/user/{id}")
	public User updateUser(@RequestBody User model, @PathVariable(name = "id") Long id) {
		model.setId(id);
//		, @RequestHeader(name = "Authorization") String token
		return uService.saveUser(model, null);
//		return model;

	}

	// api chuyển trạng thái kích hoạt tải khoản

}
