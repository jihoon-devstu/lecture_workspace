package com.sinse.mvcapp.color.model;

/*
 * MVC패턴에 의해 , 디자인 영역과 로직 및 데이터 영역은 분리시켜야 유지보수성이 높아지기 때문에.
 * 
 * */


public class ColorManager {
	
	public String getAdvice(String color) {
		String msg="";
		if(color.equals("red")){
			msg="열정적이고 활동적";
		}else if(color.equals("blue")){
			msg="신중하고 분석적";
		}else if(color.equals("yellow")){
			msg="낙천적이고 외향적";
		}else if(color.equals("pink")){
			msg="온화하고 평화적";
		}else if(color.equals("green")){
			msg="배려심이 깊음";
		}
		return msg;
	}
}
