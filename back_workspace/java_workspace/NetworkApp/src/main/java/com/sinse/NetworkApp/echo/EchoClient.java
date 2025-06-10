package com.sinse.NetworkApp.echo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;
import java.net.ServerSocket;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EchoClient extends JFrame{

	JPanel p_north;
	
	JComboBox box_ip;
	JTextField t_port;
	JButton bt;
	
	JTextArea area;
	JScrollPane scroll;
	JTextField t_input;
	
	public EchoClient() {
		//생성
		p_north = new JPanel();
		
		box_ip = new JComboBox();
		t_port = new JTextField("9999", 8);
		bt = new JButton("접속");
		area = new JTextArea();
		scroll = new JScrollPane(area);
		t_input = new JTextField();
		
		createIp();
		//스타일
		area.setBackground(Color.yellow);
		p_north.setPreferredSize(new Dimension(300,40));
		box_ip.setPreferredSize(new Dimension(200,30));
		t_port.setPreferredSize(new Dimension(60,30));
		area.setPreferredSize(new Dimension(300,220));
		t_input.setPreferredSize(new Dimension(300,40));
		
		
		//조립
		
		p_north.add(box_ip);
		p_north.add(t_port);
		p_north.add(bt);
		
		add(p_north, BorderLayout.NORTH);
		add(scroll);
		add(t_input, BorderLayout.SOUTH);
		
		//접속 버튼과 리스너 연결
		bt.addActionListener(e->{
			connect();
		});
		
		
		setSize(450,500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void connect() {
		
	}
	
	public void createIp() {
		for(int i=25;i<=47;i++) {
			box_ip.addItem("192.168.60."+i);
		}
	}
	

	
	public static void main(String[] args) {
		new EchoClient();
	}
}
