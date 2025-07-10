package mvcproject.blood.model;

import javax.swing.JOptionPane;

public class BloodManager {

//mvc에서 모델은 비즈니스 로직이 구현된 코드이므로 , 재사용의 대상이 된다(자본이 들어감...)
	public String getAdvice(String blood) {
		//혈액형에 대한 판단
		
		String msg = null;
		
		if(blood.equals("A")){
			msg="신중하고 꼼꼼함";
		}else if(blood.equals("B")){
			msg="자유롭고 개성이 강함";
		}else if(blood.equals("O")){
			msg="외향적이고 리더싶 있음";
		}else if(blood.equals("AB")){
			msg="이성적이고 독특함";
		}
		return msg;
	}
}
