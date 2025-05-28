package gui.chat;

import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.TextArea;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class ChatB extends Frame implements KeyListener{
	
	TextArea area;
	TextField t_input;
	Panel p_south;
	ChatA chatA; //chatB가 chatA를 제어하기위해 has a 관계로 보유
	
	public ChatB(ChatA chatA){ //생성자 호출시 , 주소값을 넘겨야 하므로 , 
												// 이 생성자 메서드를 호출하는 자는 call by reference
		System.out.println("저 태어날 때 ChatA정보 넘겨 받았어요"+chatA);
		area = new TextArea();	
		p_south = new Panel();
		t_input = new TextField(20);
		this.chatA = chatA; //새롭게 인스턴스를 생성하지 말고 , 기존의 chatA를 넘겨받자
		
				
		//스타일 적용
		area.setBackground(Color.ORANGE);
		
		add(area);
		p_south.add(t_input);
		add(p_south, BorderLayout.SOUTH);
		
		t_input.addKeyListener(this);
		
		this.setSize(300,400);
		this.setVisible(true);
				
	}
	//키보드 누르면서 엔터칠 때 ChatA에게 메시지 보내기 
	public void keyReleased(KeyEvent e){
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			chatA.area.append(t_input.getText()+"\n");
			t_input.setText(""); //입력 초기화
		}
	}
	
	public void keyTyped(KeyEvent e){};
	public void keyPressed(KeyEvent e){};
}
