package com.sinse.shop.common.exception;

public class MemberException extends RuntimeException{

	public MemberException(String msg) {
		super(msg);
	}
	//예외 객체들의 최상위 객체가 바로 Throwable 인터페이스.
	public MemberException(Throwable e) {
		super(e);
	}
	
	public MemberException(String msg , Throwable e) {
		super(msg , e);
	}
	
}
