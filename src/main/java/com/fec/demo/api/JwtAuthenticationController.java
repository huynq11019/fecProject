package com.fec.demo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fec.demo.DTO.JwtRequest;
import com.fec.demo.DTO.JwtResponse;
import com.fec.demo.config.JwtTokenUtil;
import com.fec.demo.exceotion.ErrorException;
import com.fec.demo.service.CustomUserDetails;
import com.fec.demo.service.JwtUserDetailsService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin

public class JwtAuthenticationController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService jwtInMemoryUserDetailsService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	@ApiOperation(value = "cho phép người dùng đăng nhập  ", notes = "đăng nhập theo só điện thoại và mặt khẩu và trả về 1 access token")
	public JwtResponse authenticateUser(@RequestBody JwtRequest authenticationRequest) // dữ liệu từ request gửi lên
	{
		// Xác thực từ username và password.
		try {
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtTokenUtil.generateToken((CustomUserDetails) authentication.getPrincipal());
			return new JwtResponse(jwt);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ErrorException("đăng tài khoản không chính xác");
		}
//		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		// Nếu không xảy ra exception tức là thông tin hợp lệ
		// Set thông tin authentication vào Security Context

		// Trả về jwt cho người dùng.

	}

//	// Api /api/random yêu cầu phải xác thực mới có thể request
//	@GetMapping("/random")
//	public RandomStuff randomStuff() {
//		return new RandomStuff("JWT Hợp lệ mới có thể thấy được message này");
//	}

}
