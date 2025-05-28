class ObjectType{
	
	public static void main(String[] args){
		//Java에서는 자료형이 총 4개가 지원된다.
		//기본 자료형(문자,숫자,논리값) + 객체자료형
		int x=5;
		
		//고정 관념에서 벗어나자, 자바에서는 개발자가 정의한 클래스를
		//새로운 자료형으로 인정해준다. 따라서 아래의 코드에서 변수 d앞에 선언해야 하는 자료형은
		//내가 정의한 Dog형이다.
		Dog d = new Dog();
		d.bark();
	}
}