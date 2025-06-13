package com.sinse.shop.common.exception;

public class EmailException extends RuntimeException{
	public EmailException(String msg) {
		super(msg);
	}
	//예외 객체들의 최상위 객체가 바로 Throwable 인터페이스.
	public EmailException(Throwable e) {
		super(e);
	}
	
	public EmailException(String msg , Throwable e) {
		super(msg , e);
	}
}
