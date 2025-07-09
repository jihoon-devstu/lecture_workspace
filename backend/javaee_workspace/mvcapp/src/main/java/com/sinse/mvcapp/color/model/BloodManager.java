package com.sinse.mvcapp.color.model;

/*
 * MVC패턴에 의해 , 디자인 영역과 로직 및 데이터 영역은 분리시켜야 유지보수성이 높아지기 때문에.
 * 
 * */


public class BloodManager {
	
	public String getAdvice(String blood) {
		String msg="";
		if(blood.equals("A")){
			msg="신중하고 꼼꼼함";
		}else if(blood.equals("B")){
			msg="자유롭고 개성이 강함";
		}else if(blood.equals("AB")){
			msg="누가뭐래도 AB형";
		}else if(blood.equals("O")){
			msg="온화하고 평화적";
		}
		return msg;
	}
}
