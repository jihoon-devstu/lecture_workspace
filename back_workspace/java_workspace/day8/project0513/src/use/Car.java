/*
최대한 현실을 반영하여 자동차를 정의해보자
조건1) 자동차의 핸들이 있어야함
조건2) 자동차의 바퀴도 있어야함
조건3) 자동차의 문짝도 있어야함


*/
package use;
public class Car{
	//has a 관계 Car has a Handle
	//객체가 다른 객체를 멤버변수로 보유한 관계를 has a 관계라 한다.
	int price;
	String name;
	
	Handle h = new Handle(); 
	Wheel w = new Wheel();
	Door d = new Door(); 
	
	//생성자는 사물을 태어나게 하는 시점에 , 초기화에 관여하므로 
	//특히 has a 관계에 있는 객체의 인스턴스를 생성할때엔 아주 유용함.
	//
	
	Public Car(){
		price = 5000;
		name="Cayenne"
	}
	
	public void setHandleColor(String h){
		this.h.color=h;
	}
	public void setWheelprice(int p){
		this.w.price=p;
	}
	public void setDoorOpen(){
		d.open();
	}
	public void setDoorclose(){
		d.close();
	}
	
}




