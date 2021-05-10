package com.fec.demo.DTO;

import java.io.Serializable;

import lombok.Data;

@Data
public class JwtRequest implements Serializable {
private String username;
private String password;
}
