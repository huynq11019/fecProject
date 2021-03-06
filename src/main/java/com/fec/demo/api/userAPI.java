package com.fec.demo.api;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fec.demo.DTO.ParentOutput;
import com.fec.demo.config.JwtTokenUtil;
import com.fec.demo.entity.User;
import com.fec.demo.entity.UserRole;
import com.fec.demo.exceotion.ErrorException;
import com.fec.demo.exceotion.MyExceptionHandler;
import com.fec.demo.service.JwtUserDetailsService;
import com.fec.demo.service.UserRoleService;
import com.fec.demo.service.UserService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@RestController
@Slf4j
//@RequestMapping("/api")
public class userAPI {
	@Autowired
	private UserService uService;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	private UserRoleService urService;
	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;

	@GetMapping(value = "/api/user")
	public ParentOutput<User> finAll(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "3") int limit, @RequestParam(defaultValue = "id") String sorby,
			@RequestParam(defaultValue = "false") boolean order) {

		return uService.listAll(page, limit, sorby, order);
	}

	// create User; không truyền khóa chính
	// đăng ký tài khoản mặc định khi đăng ký trạng thái sẽ là false

	@PostMapping(value = "/dangky")
	@ApiOperation(value = "used to register account", notes = "nhập thông tin tài khoản vào đây để đăng ký tìa khoản")
	public User createUser(@RequestBody User obj) {
		System.out.println(obj.toString());
		obj.setId(null);
		obj.setNgaygianhap(new Date());
		obj.setActive(true); // khi mới đăng ký thì trạng thái của tài khoản là false
		System.out.println(obj.toString());

		User newUser = uService.saveUser(obj);
		if (newUser == null) {
//			throw new CustomUserException("số điện thoại đã được đăng ký trong hệ thống");
//			return null;
			throw new ErrorException("user đã tồn tại trong hệ thống");
		}
		return newUser; // token == null

	}

	// tìm user theo mã
	@GetMapping(value = "/api/user/{id}")
	public User getUser(@PathVariable(name = "id") Long id) {
		User us = uService.getByid(id);
		if (us == null) {
			throw new ErrorException("User không tồn tại trong hệ thống");
		}
		return us;
	}

	// xóa tài khoản trả về một id đã bị xóa
	@ApiOperation(value = "truyền id người dùng vào để xóa cứng tài khoản", notes = "nếu xóa thành công trả về ID của người dùng đã xóa nếu không trả về kết quả là -1")
	@DeleteMapping(value = "/api/admin/user/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public long deleUser(@PathVariable(name = "id") Long id) {

		System.out.println("id vừa nhập vào " + id);
		return uService.delete(id);
//		return -1;
	}

	// cập nhật user theo id
//	@PreAuthorize("isAuthenticated")
	@PutMapping(value = "/api/user/{id}")
	public User updateUser(@RequestBody(required = true) User model, @PathVariable(name = "id") Long id) {
		model.setId(id);
		User us = uService.saveUser(model);

		return us;
//		return model;

	}

	// api chuyển trạng thái kích hoạt tải khoản
	@PutMapping(value = "/api/admin/changeUser/{id}")
	@ApiOperation(value = "mở khóa tài khoản", notes = "chức năng này cho phép admin có thể thay dổi trạng thái của tài khoản")
	public User setactive(@PathVariable(name = "id") Long id, @RequestParam(required = true) Boolean isActive) {
		// địa chỉ id phải trùng
		System.out.println("thay đổi trạng thái của tài khoản có id " + id + isActive);
		return uService.activeUser(id, isActive);
//		return null;
	}

	@GetMapping(value = "/api/user/getUserbytoken")
	@ApiOperation(value = "get thông tin user bằng AccessToken", notes = "truyền token vào header và truy cập vào đường dẫn ")
	public User getUserByToken(@RequestHeader(name = "Authorization", required = true) String bearerToken) {
		try {
			String jwt = null;

			if (bearerToken != null) {
				// Kiểm tra xem header Authorization có chứa thông tin jwt không
				if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("FEC ")) {
					jwt = bearerToken.substring(4);
				}
				if (StringUtils.hasText(jwt) && jwtTokenUtil.validateToken(jwt)) {
					Long userId = jwtTokenUtil.getUserIdFromJWT(jwt);
					// Lấy thông tin người dùng từ id
					// kiểm tra thằng vừa truy cập là thằng nào
					return uService.getByid(userId);
				}
			}
			log.error("xảy ra lỗi với access token ");

		} catch (Exception e) {
//			System.out.println("xảy ra lỗi với access token "+e);
			log.error("xảy ra lỗi với access token " + e);

		}

		throw new ErrorException("user đã tồn tại trong hệ thống");

	}

	@PostMapping("/api/admin/phanquyen")
	@ApiOperation(value = "phân quyền", notes = "chức năng này cho phép admin có thể phân quyền cho cách thành viên ")
	public User phanquyen(UserRole usertole) {
//		urService.getallUserByid();
		UserRole ur = urService.saveRoleUser(usertole);
		return ur.getUsers();
	}
}
