package com.sinse.shopadmin.product.view;

public class ExceptionTest {

	public void test() throws ClassNotFoundException{
		load();
	}
	
	public void load() throws ClassNotFoundException{
	
			Class.forName("babo.Babo");
	}

	public static void main(String[] args) throws ClassNotFoundException{
		ExceptionTest et = new ExceptionTest();
		et.test();
		
	}
}