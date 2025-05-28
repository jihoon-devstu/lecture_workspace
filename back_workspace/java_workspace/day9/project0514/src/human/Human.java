package human;

/*OOP에서 클래스 정의 시 중복되는 코드의 재사용을 위해서 상속이라는 클래스 정의법 이용 가능.*/

/*
parent : GUI 프로그래밍에서 컨테이너를 뜻함
super  : 상속관계에서 부모 객체
*/

class Human{
	
	String skinColor;
	String hair;
	
	public void tendency(){
		System.out.println("성향이 달라요");
	}

}
