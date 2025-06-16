package com.sinse.shop.common.exception;

public class ProductColorException extends RuntimeException{
	
	public ProductColorException(String msg) {
		super(msg);
	}
	//예외 객체들의 최상위 객체가 바로 Throwable 인터페이스.
	public ProductColorException(Throwable e) {
		super(e);
	}
	
	public ProductColorException(String msg , Throwable e) {
		super(msg , e);
	}
}
