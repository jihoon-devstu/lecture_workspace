package gui.chat;

import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.TextArea;
import java.awt.Button;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class ChatA extends Frame implements ActionListener, KeyListener{
	
	TextArea area;
	TextField t_input;
	Button bt_open;
	Panel p_south;
	ChatB chatB;
	
	public ChatA(){
		area = new TextArea();		
		p_south = new Panel();
		t_input = new TextField(20);
		bt_open = new Button("보내기");
				
		//스타일 적용
		area.setBackground(Color.YELLOW);
		
		add(area);
		p_south.add(t_input);
		p_south.add(bt_open);
		add(p_south, BorderLayout.SOUTH);
		
		this.setSize(300,400);
		this.setVisible(true);
		
		bt_open.addActionListener(this);
		t_input.addKeyListener(this);
				
	}
	
		//ActionListener를 구현하겠다고 선언하였으므로, 현재 클래스에서 인터페이스의
		//메서드를 오버라이딩 하자 !!!
	public void actionPerformed(ActionEvent e){
		//ChatB의 인스턴스 생성
		//this란? 인스턴스가 자기 자신을 가리키는 레퍼런스 변수
		chatB = new ChatB(this);
		}
	public void keyTyped(KeyEvent e){};
	
	public void keyPressed(KeyEvent e){};
	
	public void keyReleased(KeyEvent e){
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			String msg = t_input.getText();
			chatB.area.append(msg+"\n");
			t_input.setText("");
		};
	};
	
	public static void main(String[] args){
		new ChatA();	
	}
		
}
