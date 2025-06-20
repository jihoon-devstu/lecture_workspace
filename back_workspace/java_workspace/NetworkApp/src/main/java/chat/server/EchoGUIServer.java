package chat.server;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EchoGUIServer extends JFrame implements Runnable {
	JPanel p_north;
	JTextField t_port;
	JButton bt;
	JTextArea area;
	JScrollPane scroll;

	ServerSocket server;

	// Runnable은 쓰레드가 아니므로 , Runnable을 구현한다고 하여 , 이 객체가 쓰레드형이라고 오해하면 안됨.
	// 따라서 별도의 Thread 객체를 사용해야함... 단지 해당 Thread의 run메서드를 내가 가진 것 뿐임.
	Thread thread;

	// 클라이언트와의 대화를 위한 스트림 준비

	BufferedReader buffr; // 듣기용
	BufferedWriter buffw; // 말하기용

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

		bt.addActionListener(e -> {
			thread = new Thread(EchoGUIServer.this);
			thread.start();
		});

		setBounds(400, 100, 300, 400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void runServer() {

		try {
			server = new ServerSocket(Integer.parseInt(t_port.getText()));
			area.append("서버 생성 및 접속자 청취중..\n");

			Socket socket = server.accept();
			String ip = socket.getInetAddress().getHostAddress();
			area.append(ip + "님 접속\n");

			// 바이트 기반 , 입력 스트림(듣기)
			buffr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			// 바이트 기반 , 출력 스트림(말하기)...
			buffw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

			//클라이언트가 보낸 메시지 듣기
			while(true) {
			String msg = buffr.readLine();
			
			area.append(msg+"\n"); //서버에 로그 남기기.
			buffw.write(msg + "\n"); //버퍼기반의 스트링이므로 문자열의 끝을 알려주지 않으면 , 무한 대기에 빠짐.
			
			buffw.flush();
			}
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		runServer();
	}

	// 우리가 실행부라고 알고 있었던 존재가 사실 메인 쓰레드라 불리는 프로그램 운영 쓰레드이다.
	// 아이폰 , 안드로이드폰 , 기타 프로그램을 운영하는 메인쓰레드에게 금기시 하는 것이 있음.
	// 1) 무한 루프2) 대기 상태 (accept() , read())
	// 메인 쓰레드를 대기 상태에 빠뜨리면 , 이벤트 감지 , GUI 그래픽 처리 불가 (모든 것이 멈춤.)
	public static void main(String[] args) {
		new EchoGUIServer();
	}

}