package com.sinse.shopadmin.product.view;

public class ExceptionTest3 {

	
	public static void main(String[] args) throws NumberConvertFailException{
		
		System.out.println("A");

			throw new NumberConvertFailException("내가 만든 예외");

		//System.out.println("B");
		
	}
}
