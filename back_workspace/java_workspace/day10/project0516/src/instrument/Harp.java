package instrument;
import riding.Roller;
public class Harp extends MusicTool implements Roller{
	
	//컴파일이 성공되려면 , 부모의 불완전한 메서드를 재정의 해야함.
	public void setVolume(){
		System.out.println("소리 높이자");
	};
	
	public void roll(){
		System.out.println("굴러가요");
	}
	
}
