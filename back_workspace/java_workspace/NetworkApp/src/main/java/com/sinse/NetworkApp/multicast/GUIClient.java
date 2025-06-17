package com.sinse.NetworkApp.multicast;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GUIClient extends JFrame implements Runnable {
	JPanel p_north;
	JComboBox box_ip;
	JTextField t_port;
	JButton bt;
	JTextArea area;
	JScrollPane scroll;
	JTextField t_input;

	Thread thread; // 접속 지연 시, 실행부가 대기 상태에 빠질 수 있으므로 ,메인쓰레드로 시도하지 말고,
					// 별도의 쓰레드로 진행 --> 접속 쓰레드
	
	ClientChatThread chatThread; //채팅용 쓰레드

	public GUIClient() {
		p_north = new JPanel();
		box_ip = new JComboBox();
		t_port = new JTextField("9999", 8);
		bt = new JButton("접속");
		area = new JTextArea();
		scroll = new JScrollPane(area);
		t_input = new JTextField();
		thread = new Thread(GUIClient.this);
		

		area.setBackground(Color.YELLOW);

		createIp();

		p_north.add(box_ip);
		p_north.add(t_port);
		p_north.add(bt);

		add(p_north, BorderLayout.NORTH);
		add(scroll);
		add(t_input, BorderLayout.SOUTH);

		bt.addActionListener(e -> {
			thread.start();
		});

		t_input.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					// 보내고
					chatThread.send(t_input.getText());
					t_input.setText("");
					// 듣자
				}
			}
		});

		setBounds(400, 300, 300, 400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}


	public void connectServer() {

		try {
			Socket socket = new Socket((String) box_ip.getSelectedItem(), Integer.parseInt(t_port.getText()));
			
			chatThread = new ClientChatThread(this,socket);
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void run() {
		connectServer();

	}

	public void createIp() {
		for (int i = 15; i <= 60; i++) {
			box_ip.addItem("192.168.60." + i);
		}
	}

	public static void main(String[] args) {
		new GUIClient();
	}

}