package com.sinse.hiberasync.exception;

public class StoreException extends RuntimeException{
	public StoreException() {
		
	}

	public StoreException (String msg) {
		super(msg); //생성자는 물려받지 못하므로 , 부모의 생성자를 호출한다
	}
	
	public StoreException(String msg , Throwable e) {
		super(msg , e);
	
	}
	
	public StoreException(Throwable e) {
		super(e);
	}
}
