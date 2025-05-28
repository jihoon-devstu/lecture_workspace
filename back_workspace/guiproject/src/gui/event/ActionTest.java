package gui.event;
import java.awt.*;

public class ActionTest {
	public static void main(String[] args) 
	{
		Frame frame = null;
		Button bt = null;
		TextField t = null;
		Choice ch = null; //html 에서의 select 박스
		
		frame = new Frame("Action Test");
		bt = new Button("Click me !");
		t = new TextField(20);
		ch = new Choice();
		
		ch.addItem("choose your mail server");
		ch.addItem("@naver.com");
		ch.addItem("@gmail.com");
		ch.addItem("@daum.net");
		
		//js에서 처럼 bt.addEventListener() 메서드를 버튼에 연결하는 과정 진행
		MyActionListener E = null;
		E = new MyActionListener();
		
		MyKeyListener K = null;
		K = new MyKeyListener();
		
		MyItemListener I = null;
		I = new MyItemListener();
		
		MyMouseListener M = null;
		M = new MyMouseListener();
		
		bt.addActionListener(E); //이벤트를 구현한 객체의 인스턴스
		t.addKeyListener(K);
		ch.addItemListener(I);
		frame.addMouseListener(M);
		
		frame.setLayout(new FlowLayout());
		frame.add(bt);
		frame.add(t);
		frame.add(ch);
		
		frame.setSize(300,400);
		frame.setVisible(true);
	}
}
