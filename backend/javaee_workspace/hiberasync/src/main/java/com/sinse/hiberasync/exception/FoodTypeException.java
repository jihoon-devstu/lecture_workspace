package com.sinse.hiberasync.exception;

public class FoodTypeException extends RuntimeException{
	public FoodTypeException() {
		
	}

	public FoodTypeException (String msg) {
		super(msg); //생성자는 물려받지 못하므로 , 부모의 생성자를 호출한다
	}
	
	public FoodTypeException(String msg , Throwable e) {
		super(msg , e);
	
	}
	
	public FoodTypeException(Throwable e) {
		super(e);
	}
}
