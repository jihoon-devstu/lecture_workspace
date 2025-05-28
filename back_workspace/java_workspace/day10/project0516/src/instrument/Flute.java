package instrument;

//추상 클래스를 상속받는 자식은, 반드시 부모의 부로안전한 메서드인
//추상 메서드를 완전하게 구현할 의무를 가진다. (구현 강제)

public class Flute extends MusicTool{
	//부모의 메서드를 자식이 재정의하는 정의 기법 
	//즉 오버라이딩 의무가 생긴다
	
	public void setVolume(){
		
	}
	
}
