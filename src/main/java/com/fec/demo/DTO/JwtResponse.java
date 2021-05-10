package com.fec.demo.DTO;

import java.io.Serializable;

import lombok.Data;

@Data
public class JwtResponse  {

	private String accessToken;
	private String tokenType = "Bearer";
	public JwtResponse(String accessToken) {
		super();
		this.accessToken = accessToken;
	}

}
