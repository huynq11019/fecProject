package com.fec.demo.exceotion;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {
	private HttpStatus status;

	private String message;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime time;
	private String debugMessage;

	public ApiError(HttpStatus status, String message) {
		super();
		this.status = status;
		this.message = message;
		this.time = LocalDateTime.now();
	}

//	public ApiError(HttpStatus status) {
//		this();
//		this.status = status;
//	}
//
//	public ApiError(HttpStatus status, Throwable ex) {
//		this();
//		this.status = status;
//		this.message = "Unexpected error";
//		this.debugMessage = ex.getLocalizedMessage();
//	}
//
//	public ApiError(HttpStatus status, String message, Throwable ex) {
//		this();
//		this.status = status;
//		this.message = message;
//		this.debugMessage = ex.getLocalizedMessage();
//	}

}
