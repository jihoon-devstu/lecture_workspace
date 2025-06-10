package com.sinse.NetworkApp.echo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.IOException;
import java.net.ServerSocket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EchoGUIServer extends JFrame{
	JPanel p_north;
	JTextField t_port;
	JButton bt;
	JTextArea area;
	JScrollPane scroll;
	
	ServerSocket server;
	Thread thread;
	
	public EchoGUIServer() {
		p_north = new JPanel();
		t_port = new JTextField(8);
		bt = new JButton("서버 가동");
		area = new JTextArea();
		scroll = new JScrollPane(area);
		
		area.setBackground(Color.yellow);
		
		p_north.add(t_port);
		p_north.add(bt);
	
		
		add(p_north, BorderLayout.NORTH);
		add(scroll);
		
		bt.addActionListener(e->{
			thread = new Thread() {
				public void run() {
					runServer();
				}
			};
			thread.start();
		});
		
		
	
		setBounds(400, 100, 300,400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void runServer() {
		String port = t_port.getText();
		
		try {
			server = new ServerSocket(Integer.parseInt(port));
			area.append("서버 생성 및 접속자 청취중..\n");
			
			server.accept();
			area.append("접속자 발견\n");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//우리가 실행부라고 알고 있었던 존재가 사실 메인 쓰레드라 불리는 프로그램 운영 쓰레드이다.
	//아이폰 , 안드로이드폰 , 기타 프로그램을 운영하는 메인쓰레드에게 금기시 하는 것이 있음.
	//1) 무한 루프2) 대기 상태 (accept() , read())
	//메인 쓰레드를 대기 상태에 빠뜨리면 , 이벤트 감지 , GUI 그래픽 처리 불가 (모든 것이 멈춤.)
	public static void main(String[] args) {
		new EchoGUIServer();
	}
	
}