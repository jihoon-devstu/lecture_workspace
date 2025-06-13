package com.sinse.shopadmin.common.exception;

public class ProductImgException extends RuntimeException{
	public ProductImgException(String msg) {
		super(msg);
	}
	//예외 객체들의 최상위 객체가 바로 Throwable 인터페이스.
	public ProductImgException(Throwable e) {
		super(e);
	}
	
	public ProductImgException(String msg , Throwable e) {
		super(msg , e);
	}
}
