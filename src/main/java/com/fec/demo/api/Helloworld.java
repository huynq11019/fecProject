package com.fec.demo.api;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class Helloworld  {
	   @GetMapping("/api/test")
	    public ResponseEntity<String> testSpringBoot() {
	        return ResponseEntity.ok("Success");
	    }

}
