package com.fec.demo.exceotion;

public class NotFoundError extends RuntimeException {
	public NotFoundError(String e) {
		super(e);
	}
}
