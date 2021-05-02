package com.fec.demo.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fec.demo.DTO.TestDTO;

@RestController
public class testAPI {
	@PostMapping(value = "/api/settest")
	public TestDTO createTest(@RequestBody TestDTO model) {
		return model;
	}
	
}
