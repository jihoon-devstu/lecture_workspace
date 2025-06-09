package com.sinse.shopadmin.product.repository;

public class Duck {
	String name = "도널드";
	private static Duck d;
	
	private Duck() {
		
	}
	
	public static Duck getD() {
		if(d==null) {
			d=new Duck();
		}
		return d;
	}
}
