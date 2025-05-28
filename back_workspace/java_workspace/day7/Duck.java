class Duck{

	/*
	프로그램 작성 시, 개발자는 변수와 메서드명에 상당한 공을 들여야함.
	하지만, 개발을 하다보면, 비슷한 기능의 메서드임에도 불구하고, 메서드명 중복 금지라는
	원칙을 너무 고수하면 , 메서드를 여러개 만들어야 하는 상황 발생...
	효율성이 떨어지므로 java, c#등 oop의 경우엔 기능상 크게 차이가 없는 경우,
	기존의 메서드명은 중복정의하는 기법을 지원하는데 , 이를 
	메서드 오버로딩(OverLoading) : 메서드 중첩 기법
	단 , 오버로딩을 인정받기 위해서는 아래의 조건을 만족해야 함
	1) 메서드의 이름이 동일
	2) 매개변수의 자료형이나 갯수가 달라야 함.*/

	public void fly(int high){};
	
	public void fly(int ){};
	
	public void littehighfly(){};
	
	public void littlehigherfly(){};

}