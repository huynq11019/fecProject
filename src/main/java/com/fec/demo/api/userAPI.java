package com.fec.demo.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fec.demo.entity.User;
import com.fec.demo.service.UserService;

@CrossOrigin
@RestController
public class userAPI {
	@Autowired
	private UserService uService;

	@GetMapping(value = "/api/user")
	public List<User> finAll() {
		return uService.listAll();
	}

	// create User; không truyền khóa chính
	@PostMapping(value = "/api/user")
	public User createUser(@RequestBody User obj) {
		obj.setId(null);
		System.out.println(obj.toString());
		return uService.saveUser(obj);

	}

	// tìm user theo mã
	@GetMapping(value = "/api/user/{id}")
	public User getUser(@PathVariable(name = "id") Long id) {
		return uService.getByid(id);
	}

	// xóa tài khoản trả về một id đã bị xóa
	@DeleteMapping(value = "/api/user/{id}")
	public long deleUser(@PathVariable(name = "id") Long id) {
		return uService.delete(id);
	}

	// cập nhật user theo id
	@PutMapping(value = "/api/user/{id}")
	public User updateUser(@RequestBody User model, @PathVariable(name = "id") Long id) {
		model.setId(id);
		return uService.saveUser(model);

	}
}
