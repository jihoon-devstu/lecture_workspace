package com.sinse.shopadmin.common.exception;

public class ProductSizeException extends RuntimeException{
	public ProductSizeException(String msg) {
		super(msg);
	}
	//예외 객체들의 최상위 객체가 바로 Throwable 인터페이스.
	public ProductSizeException(Throwable e) {
		super(e);
	}
	
	public ProductSizeException(String msg , Throwable e) {
		super(msg , e);
	}
}
