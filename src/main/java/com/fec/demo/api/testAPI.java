package com.fec.demo.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fec.demo.DTO.TestDTO;

@RestController
public class testAPI {
	@GetMapping(value = "/api/settest")
	public String createTest() {
		return "helo";
	}
	
}
