package com.fec.demo.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fec.demo.DTO.TestDTO;

@Controller
public class testAPI {
	@RequestMapping( "/api/admin/settest")
	public String createTest() {
		System.out.println("test login");
		return "login";
	}
	
}
