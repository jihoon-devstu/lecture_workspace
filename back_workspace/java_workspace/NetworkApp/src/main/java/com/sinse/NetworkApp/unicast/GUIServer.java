package com.sinse.NetworkApp.unicast;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GUIServer extends JFrame implements Runnable{
	JPanel p_north;
	JTextField t_port;
	JButton bt;
	JTextArea area;
	JScrollPane scroll;
	Thread thread; //서버 가동 시 , 메인 쓰레드가 accept()에서 대기 상태에 빠지지 않게 하기 위해.
	
	public GUIServer() {
		p_north = new JPanel();
		t_port = new JTextField("9999",8);
		bt = new JButton("서버 가동");
		area = new JTextArea();
		scroll = new JScrollPane(area);
		
		area.setBackground(Color.YELLOW);
		
		p_north.add(t_port);
		p_north.add(bt);
		add(p_north, BorderLayout.NORTH);
		add(scroll);
	
		
		bt.addActionListener(e->{
			thread = new Thread();
		});
		
		
		setBounds(2300, 100, 300,400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new GUIServer();
	}
	
}