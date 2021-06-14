package com.fec.demo.exceotion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



public class ErrorException extends RuntimeException {

	public ErrorException(String messsage) {
		// TODO Auto-generated constructor stub
		super(messsage);
	}

}
