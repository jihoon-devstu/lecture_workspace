package gui.event;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

/*os를 거쳐 JVM으로부터 전달되는 키보드 이벤트를 청취하기 위한
객체인 KeyListener를 재정의해보자*/

public class MyKeyListener implements KeyListener{
	
	//KeyListener가 보유한 추상 메서드 재정의 {} 만 붙이기
	public void keyTyped(KeyEvent e){
		System.out.println("눌렀다가 떼었어?");
	};
	
	public void keyPressed(KeyEvent e){
		System.out.println("눌렀어?");
	};
	
	public void keyReleased(KeyEvent e){};
	
}

